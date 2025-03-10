package me.jellysquid.mods.sodium.client.gui.options.control;

import me.jellysquid.mods.sodium.client.gui.options.Option;
import me.jellysquid.mods.sodium.client.gui.widgets.AbstractWidget;
import me.jellysquid.mods.sodium.client.util.Dim2i;
import net.minecraft.util.text.TextFormatting;

public class ControlElement<T> extends AbstractWidget {
    protected final Option<T> option;

    protected final Dim2i dim;

    protected boolean hovered;

    public ControlElement(Option<T> option, Dim2i dim) {
        this.option = option;
        this.dim = dim;
    }

    public boolean isHovered() {
        return this.hovered;
    }

    @Override
    public void render(int mouseX, int mouseY, float delta) {
        String name = this.option.getName();
        String label;

        if (this.hovered && this.font.getStringWidth(name) > (this.dim.getWidth() - this.option.getControl().getMaxWidth())) {
            name = name.substring(0, Math.min(name.length(), 10)) + "...";
        }

        if (this.option.isAvailable()) {
            if (this.option.hasChanged()) {
                label = TextFormatting.ITALIC + name + " *";
            } else {
                label = TextFormatting.WHITE + name;
            }
        } else {
            label = String.valueOf(TextFormatting.GRAY) + TextFormatting.STRIKETHROUGH + name;
        }

        this.hovered = this.dim.containsCursor(mouseX, mouseY);


        this.drawRect(this.dim.getOriginX(), this.dim.getOriginY(), this.dim.getLimitX(), this.dim.getLimitY(), this.hovered ? 0xE0000000 : 0x90000000);
        this.drawString(label, this.dim.getOriginX() + 6, this.dim.getCenterY() - 4, 0xFFFFFFFF);
    }

    public Option<T> getOption() {
        return this.option;
    }

    public Dim2i getDimensions() {
        return this.dim;
    }
}
