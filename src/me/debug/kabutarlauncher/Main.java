package me.debug.kabutarlauncher;

import me.debug.kabutarlauncher.Util.OSHelper;
import me.debug.kabutarlauncher.Util.unziputil;
import me.debug.kabutarlauncher.ui.LauncherFrame;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Main extends LauncherFrame {
    public Main() throws IOException, FontFormatException {
        main(null);
    }



    public static void main(String[] args) throws IOException, FontFormatException {

        new LauncherFrame();
        //launch();
    }

    public static void launch() throws IOException, FontFormatException {
        LauncherFrame launchergui = new LauncherFrame();

        Main main = new Main();

        File mineraftDirectory = new File(OSHelper.getOS().getMc());
        File mineraftAssets = new File(mineraftDirectory.toString() + "Assets");
        File natives = new File(System.getProperty("user.dir") + File.separator + "Natives.zip");
        File libraries = new File(System.getProperty("user.dir") + File.separator + "libraries.zip");
        File jars = new File(System.getProperty("user.dir") + File.separator + "LabyMod-3-1.8.9.jar");

        /*FileUtils.copyURLToFile(new URL("https://drive.google.com/uc?export=download&id=1AYpx_rPRvEm-il_RDSXtgX0paRdWRtAx"), natives);
        FileUtils.copyURLToFile(new URL("https://drive.google.com/uc?export=download&id=19xjb6PUQbmyxKh23GvkmXCpdJNZp17uq"), libraries);
        FileUtils.copyURLToFile(new URL("https://drive.google.com/uc?export=download&id=1ibcEz2dBb-NHkWExHUKw35M8Vh9ULlBE), jars);
        */


        try{

            System.out.println("Downloading Files...");
            System.out.println("Downloading Jar...");
            FileUtils.copyURLToFile(new URL("https://drive.google.com/uc?export=download&id=1bGg4c-Zkb0nT34K4KvlWF8KEAFLigoUf"), jars);
            System.out.println("Downloading Natives...");
            FileUtils.copyURLToFile(new URL("https://drive.google.com/uc?export=download&id=1AYpx_rPRvEm-il_RDSXtgX0paRdWRtAx"), natives);
            System.out.println("Downloading Libs");
            FileUtils.copyURLToFile(new URL("https://drive.google.com/uc?export=download&id=19xjb6PUQbmyxKh23GvkmXCpdJNZp17uq"), libraries);

        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        unziputil unziper = new unziputil();

        try {
            System.out.println("Unziping Files...");
            unziper.unzip(natives.toString(), System.getProperty("user.dir") + File.separator + "Natives");
            natives.delete();

            unziper.unzip(libraries.toString(), System.getProperty("user.dir") + File.separator + "Libraries");
            libraries.delete();




        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            System.out.println("Launching...");
            Process process = Runtime.getRuntime().exec("java -"
                    + "Xms1024m "
                    + "-Xmx4096m "
                    + "-Djava.library.path=\"" + System.getProperty("user.dir") + File.separator + "natives" + "\" "
                    + "-cp \"" + System.getProperty("user.dir") + File.separator + "Libraries" + File.separator + "*" + ";" + jars.toString() + "\" "
                    + "net.minecraft.client.main.Main "
                    + "--width 854 "
                    + "--height 480 "
                    + "--version 1.8.8"
                    + "--gameDir " + mineraftDirectory.toString() + " "
                    + "--assetsDir " + mineraftAssets.toString() + " "
                    + "assetIndex 1.8.8 "
                    + "uuid N/A "
                    + "--accessToken DBnoobs "
                    + "--userType free");


            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String s = null;
            while ((s = stdInput.readLine()) != null){
                System.out.println(s);
            }
            while ((s = stdError.readLine()) != null){
                System.out.println(s);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

