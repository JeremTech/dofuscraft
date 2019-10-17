package fr.dofuscraft.dofuscraftcore.gui;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.Language;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class GuiDCLanguage extends GuiScreen {
    /**
     * The parent Gui screen
     */
    protected GuiScreen parentScreen;
    /**
     * The List GuiSlot object reference.
     */
    private fr.dofuscraft.dofuscraftcore.gui.GuiDCLanguage.List list;
    /**
     * Reference to the GameSettings object.
     */
    private final GameSettings game_settings_3;
    /**
     * Reference to the LanguageManager object.
     */
    private final LanguageManager languageManager;
    /**
     * A button which allows the user to determine if the Unicode font should be forced.
     */
    private GuiOptionButton forceUnicodeFontBtn;
    /**
     * The button to confirm the current settings.
     */
    private GuiOptionButton confirmSettingsBtn;

    public GuiDCLanguage(GuiScreen screen, GameSettings gameSettingsObj, LanguageManager manager) {
        this.parentScreen = screen;
        this.game_settings_3 = gameSettingsObj;
        this.languageManager = manager;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui() {
        this.forceUnicodeFontBtn = (GuiOptionButton) this.addButton(new GuiOptionButton(100, this.width / 2 - 155, this.height - 38, GameSettings.Options.FORCE_UNICODE_FONT, this.game_settings_3.getKeyBinding(GameSettings.Options.FORCE_UNICODE_FONT)));
        this.confirmSettingsBtn = (GuiOptionButton) this.addButton(new GuiOptionButton(6, this.width / 2 - 155 + 160, this.height - 38, I18n.format("gui.done")));
        this.list = new fr.dofuscraft.dofuscraftcore.gui.GuiDCLanguage.List(this.mc);
        this.list.registerScrollButtons(7, 8);
    }

    /**
     * Handles mouse input.
     */
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        this.list.handleMouseInput();
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.enabled) {
            switch (button.id) {
                case 5:
                    break;
                case 6:
                    this.mc.displayGuiScreen(this.parentScreen);
                    break;
                case 100:

                    if (button instanceof GuiOptionButton) {
                        this.game_settings_3.setOptionValue(((GuiOptionButton) button).getOption(), 1);
                        button.displayString = this.game_settings_3.getKeyBinding(GameSettings.Options.FORCE_UNICODE_FONT);
                        ScaledResolution scaledresolution = new ScaledResolution(this.mc);
                        int i = scaledresolution.getScaledWidth();
                        int j = scaledresolution.getScaledHeight();
                        this.setWorldAndResolution(this.mc, i, j);
                    }

                    break;
                default:
                    this.list.actionPerformed(button);
            }
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.list.drawScreen(mouseX, mouseY, partialTicks);
        this.drawCenteredString(this.fontRenderer, I18n.format("options.language"), this.width / 2, 16, 16777215);
        this.drawCenteredString(this.fontRenderer, "(" + I18n.format("options.languageWarning") + ")", this.width / 2, this.height - 56, 8421504);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @SideOnly(Side.CLIENT)
    class List extends GuiSlot {
        /**
         * A list containing the many different locale language codes.
         */
        private final java.util.List<String> langCodeList = Lists.<String>newArrayList();
        /**
         * The map containing the Locale-Language pairs.
         */
        private final Map<String, Language> languageMap = Maps.<String, Language>newHashMap();

        public List(Minecraft mcIn) {
            super(mcIn, fr.dofuscraft.dofuscraftcore.gui.GuiDCLanguage.this.width, fr.dofuscraft.dofuscraftcore.gui.GuiDCLanguage.this.height, 32, fr.dofuscraft.dofuscraftcore.gui.GuiDCLanguage.this.height - 65 + 4, 18);

            for (Language language : fr.dofuscraft.dofuscraftcore.gui.GuiDCLanguage.this.languageManager.getLanguages()) {
                this.languageMap.put(language.getLanguageCode(), language);
                this.langCodeList.add(language.getLanguageCode());
            }
        }

        protected int getSize() {
            return this.langCodeList.size();
        }

        /**
         * The element in the slot that was clicked, boolean for whether it was double clicked or not
         */
        protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
            Language language = this.languageMap.get(this.langCodeList.get(slotIndex));
            fr.dofuscraft.dofuscraftcore.gui.GuiDCLanguage.this.languageManager.setCurrentLanguage(language);
            fr.dofuscraft.dofuscraftcore.gui.GuiDCLanguage.this.game_settings_3.language = language.getLanguageCode();
            net.minecraftforge.fml.client.FMLClientHandler.instance().refreshResources(net.minecraftforge.client.resource.VanillaResourceType.LANGUAGES);
            fr.dofuscraft.dofuscraftcore.gui.GuiDCLanguage.this.fontRenderer.setUnicodeFlag(fr.dofuscraft.dofuscraftcore.gui.GuiDCLanguage.this.languageManager.isCurrentLocaleUnicode() || fr.dofuscraft.dofuscraftcore.gui.GuiDCLanguage.this.game_settings_3.forceUnicodeFont);
            fr.dofuscraft.dofuscraftcore.gui.GuiDCLanguage.this.fontRenderer.setBidiFlag(fr.dofuscraft.dofuscraftcore.gui.GuiDCLanguage.this.languageManager.isCurrentLanguageBidirectional());
            fr.dofuscraft.dofuscraftcore.gui.GuiDCLanguage.this.confirmSettingsBtn.displayString = I18n.format("gui.done");
            fr.dofuscraft.dofuscraftcore.gui.GuiDCLanguage.this.forceUnicodeFontBtn.displayString = fr.dofuscraft.dofuscraftcore.gui.GuiDCLanguage.this.game_settings_3.getKeyBinding(GameSettings.Options.FORCE_UNICODE_FONT);
            fr.dofuscraft.dofuscraftcore.gui.GuiDCLanguage.this.game_settings_3.saveOptions();
        }

        /**
         * Returns true if the element passed in is currently selected
         */
        protected boolean isSelected(int slotIndex) {
            return ((String) this.langCodeList.get(slotIndex)).equals(fr.dofuscraft.dofuscraftcore.gui.GuiDCLanguage.this.languageManager.getCurrentLanguage().getLanguageCode());
        }

        /**
         * Return the height of the content being scrolled
         */
        protected int getContentHeight() {
            return this.getSize() * 18;
        }

        protected void drawBackground() {
            fr.dofuscraft.dofuscraftcore.gui.GuiDCLanguage.this.drawDefaultBackground();
        }

        protected void drawSlot(int slotIndex, int xPos, int yPos, int heightIn, int mouseXIn, int mouseYIn, float partialTicks) {
            fr.dofuscraft.dofuscraftcore.gui.GuiDCLanguage.this.fontRenderer.setBidiFlag(true);
            fr.dofuscraft.dofuscraftcore.gui.GuiDCLanguage.this.drawCenteredString(fr.dofuscraft.dofuscraftcore.gui.GuiDCLanguage.this.fontRenderer, ((Language) this.languageMap.get(this.langCodeList.get(slotIndex))).toString(), this.width / 2, yPos + 1, 16777215);
            fr.dofuscraft.dofuscraftcore.gui.GuiDCLanguage.this.fontRenderer.setBidiFlag(fr.dofuscraft.dofuscraftcore.gui.GuiDCLanguage.this.languageManager.getCurrentLanguage().isBidirectional());
        }
    }

}

