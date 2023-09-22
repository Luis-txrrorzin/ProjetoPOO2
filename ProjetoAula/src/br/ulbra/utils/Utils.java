package br.ulbra.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Utils {
    public static int salvarBoolean(boolean valor) {
        if(valor = true)
            return 1;
        else
            return 0;
    
    }

     public static Icon fileParaIcon(File file) {
        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
        return icon;
        }

    public static ImageIcon redimensionarIcon(Icon originalIcon, int largura, int altura) {
        Image imageOriginal = ((ImageIcon) originalIcon).getImage();
        Image novaImagem = imageOriginal.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        return new ImageIcon(novaImagem);
    }
   
}
