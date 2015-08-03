package net.timeless.jurassicraft.client.gui.app;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.client.gui.GuiPaleoPad;
import net.timeless.jurassicraft.common.entity.data.JCPlayerData;
import net.timeless.jurassicraft.common.entity.data.JCPlayerDataClient;
import net.timeless.jurassicraft.common.message.MessageRequestFile;
import net.timeless.jurassicraft.common.paleopad.App;
import net.timeless.jurassicraft.common.paleopad.AppBrowser;
import net.timeless.jurassicraft.common.paleopad.JCFile;

import java.util.List;

public class GuiAppBrowser extends GuiApp
{
    private static final ResourceLocation texture = new ResourceLocation(JurassiCraft.modid, "textures/gui/paleo_pad/apps/browser.png");

    private boolean intro;

    public GuiAppBrowser(App app)
    {
        super(app);
    }

    private boolean requested;

    @Override
    public void render(int mouseX, int mouseY, GuiPaleoPad gui)
    {
        super.renderButtons(mouseX, mouseY, gui);

        AppBrowser app = (AppBrowser) getApp();

        if(intro)
        {
            gui.drawScaledText("Hello " + mc.thePlayer.getName() + "! Welcome to " + app.getName() + "!", 4, 10, 1.0F, 0xFFFFFF);
            mc.getTextureManager().bindTexture(texture);
            gui.drawScaledTexturedModalRect(1, 20, 0, 0, 32, 32, 32, 32, 1.0F);
            gui.drawScaledText("Using " + app.getName() + " you can browse all your files!", 34, 29, 0.7F, 0xFFFFFF);
        }
        else
        {
            String path = app.getPath();

            List<JCFile> filesAtPath = JCPlayerDataClient.getPlayerData().getFilesAtPath(path);

            if(filesAtPath == null)
            {
                if(!requested)
                {
                    requested = true;
                    JurassiCraft.networkManager.networkWrapper.sendToServer(new MessageRequestFile(path));
                }
                else
                {
                    gui.drawScaledText("Downloading files...", 4, 10, 1.0F, 0xFFFFFF);
                }
            }
            else
            {
                requested = false;

                int y = 5;

                for (JCFile file : filesAtPath)
                {
                    if(file != null && file.getName().length() > 0)
                    {
                        gui.drawBoxOutline(5, y, 207, 12, 1, 1.0F, 0x606060);
                        gui.drawScaledText(file.getName(), 7, y + 3, 1.0F, 0xFFFFFF);
                        gui.drawScaledText(file.isFile() ? "       File" : "Directory", 160, y + 3, 1.0F, 0x7F7F7F);

                        y += 15;
                    }
                }

                gui.drawScaledRect(217, 5, 7, 140, 1.0F, 0x7F7F7F);
                gui.drawBoxOutline(217, 5, 7, 140, 1, 1.0F, 0x606060);
            }
//            gui.drawBoxOutline(10, 10, 100, 15, 1, 1.0F, 0x606060);
        }
    }

    @Override
    public void actionPerformed(GuiButton button)
    {

    }

    @Override
    public void init()
    {
        intro = !app.hasBeenPreviouslyOpened();
    }

    @Override
    public ResourceLocation getTexture(GuiPaleoPad gui)
    {
        return texture;
    }
}
