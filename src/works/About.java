/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package works;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Anonymous
 */
public class About {
    
    // this is the label of about developer screen
    JLabel aboutDev;
    JButton facebookLinkLabel;
    JButton twitterLinkLabel;
    JButton instagramLinkLabel;
    Desktop d = Desktop.getDesktop();
    /**
     * this method will make about panel
     * @param panel of the about
     */
    public void makeAboutPanel(JPanel panel){
    
        
        aboutDev= new JLabel("");
        facebookLinkLabel = new JButton("Facebook");
        twitterLinkLabel = new JButton("Twitter");
        instagramLinkLabel = new JButton("Instagram");
        
        aboutDev.setBounds(200, 100, 540, 200);
        aboutDev.setText("<html>This software is developed by <br><br>Shah Nizar Baloch (Software Engineer).<br><br>"
                + "For any queries, you can call me +923213622609<br><br>"
                + "You can contact me with below options too.<br><br>"
                + "</html>");
        aboutDev.setFont(new Font("Arial",0,15));
        aboutDev.setForeground(Color.BLACK);
        panel.add(aboutDev);
        panel.add(facebookLinkLabel);
        //facebookLinkLabel.setIcon(new ImageIcon("src\\images\\facebook.png"));
        facebookLinkLabel.setBounds(200, 300, 100, 40);
        facebookLinkLabel.setFont(new Font("Arial", Font.BOLD, 12));
        
        panel.add(twitterLinkLabel);
        //twitterLinkLabel.setIcon(new ImageIcon("src\\images\\twitter.png"));
        twitterLinkLabel.setBounds(310, 300, 100, 40);
        
        twitterLinkLabel.setFont(new Font("Arial", Font.BOLD, 12));
        
        panel.add(instagramLinkLabel);
        
        instagramLinkLabel.setFont(new Font("Arial", Font.BOLD, 12));
        //instagramLinkLabel.setIcon(new ImageIcon("src\\images\\instagram.png"));
        instagramLinkLabel.setBounds(420, 300, 100, 40);
        
        
        facebookLinkLabel.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    d.browse(new URI("https://www.facebook.com/shahnizarbaloch"));
                } catch (URISyntaxException | IOException ex) {
                    
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                facebookLinkLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                facebookLinkLabel.setBorder(null);
            }
        });
        
        twitterLinkLabel.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    d.browse(new URI("https://www.twitter.com/shahnizarbaloch"));
                } catch (URISyntaxException | IOException ex) {
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                twitterLinkLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                twitterLinkLabel.setBorder(null);
            }
        });
        
        
        instagramLinkLabel.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    d.browse(new URI("https://www.instagram.com/shahnizarbaloch"));
                } catch (URISyntaxException | IOException ex) {
                    
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                instagramLinkLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                instagramLinkLabel.setBorder(null);
            }
        });
        
    }
    
    
    
}
