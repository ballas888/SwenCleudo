package cluedo.render;

import javax.swing.JPanel;

public class HUDData {
	private JPanel HUD;
	private JPanel CardHUD;
	private JPanel InfoHud;
	
	public JPanel getHUD() {
		return HUD;
	}
	
	public void setHUD(JPanel hUD) {
		HUD = hUD;
	}
	
	public JPanel getCardHUD() {
		return CardHUD;
	}
	
	public void setCardHUD(JPanel cardHUD) {
		CardHUD = cardHUD;
	}
	
	public JPanel getInfoHud() {
		return InfoHud;
	}
	
	public void updateCards(){
		((CardHUD) CardHUD).updateCards();
	}
	
	public void updateInfo(){
		((InfoHUD) InfoHud).drawInfo();
	}
	
	public void updateHUD(){
		((HUD) HUD).updateHUD();
	}
	
	public void setInfoHud(JPanel infoHud) {
		InfoHud = infoHud;
	}
	
	
}
