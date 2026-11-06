package com.example.uprightelytra;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ConfigScreen extends Screen {
    private final Screen parent;

    public ConfigScreen(Screen parent) {
        super(Text.literal("Upright Elytra"));
        this.parent = parent;
    }

    private Text label() {
        Text state = ModConfig.enabled
                ? Text.literal("ON").formatted(Formatting.GREEN)
                : Text.literal("OFF").formatted(Formatting.RED);
        return Text.literal("Mod: ").append(state);
    }

    @Override
    protected void init() {
        addDrawableChild(ButtonWidget.builder(label(), button -> {
            ModConfig.enabled = !ModConfig.enabled;
            ModConfig.save();
            button.setMessage(label());
        }).dimensions(this.width / 2 - 100, this.height / 2 - 20, 200, 20).build());

        addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, button -> close())
                .dimensions(this.width / 2 - 100, this.height / 2 + 10, 200, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title,
                this.width / 2, this.height / 2 - 50, 0xFFFFFF);
    }

    @Override
    public void close() {
        this.client.setScreen(parent);
    }
}
