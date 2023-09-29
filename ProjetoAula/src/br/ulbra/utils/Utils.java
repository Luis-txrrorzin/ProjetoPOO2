/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ulbra.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author beeme
 */
public class Utils {
    public static int salvarBoolean(boolean valor) {
        if(valor = true)
            return 1;
        else
            return 0;
    
    }
    
    public static Icon fileParaIcon (File file) {
    ImageIcon icon = new ImageIcon(file.getAbsolutePath());
    return icon;
    }
    
    public static ImageIcon redimensionarIcon (Icon originalIcon,
            int largura, int altura) {
        Image imagemOriginal = ((ImageIcon) originalIcon).getImage();
        Image novaImagem = imagemOriginal.getScaledInstance(
                largura, altura, Image.SCALE_SMOOTH);
        return new ImageIcon(novaImagem);
    }
    
    public static byte[] iconToBytes (Icon icon) throws IOException {
        BufferedImage image = new BufferedImage(
                    icon.getIconWidth(),
                    icon.getIconHeight(),
                    BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d  = image.createGraphics();
        icon.paintIcon(null, g2d, 0, 0);
        g2d.dispose();
        
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
