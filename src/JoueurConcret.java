import java.util.ArrayList;
import java.util.Scanner;

public class JoueurConcret extends Joueur
{
	private static JoueurConcret instance;
	
	private JoueurConcret(Partie partie, String pseudoEnregistre, Joueur joueurSuivant, Joueur joueurPrecedent, ArrayList<Joueur> adversaires) 
	{
		super(partie, pseudoEnregistre, joueurSuivant, joueurPrecedent, adversaires);
	}
	
	public static Joueur creerJoueurConcret(Partie partie, String pseudoEnregistre, Joueur joueurSuivant, Joueur joueurPrecedent, ArrayList<Joueur> adversaires) 
	{
		if(instance == null)
			instance = new JoueurConcret(partie, pseudoEnregistre, joueurSuivant, joueurPrecedent, adversaires);
		return(instance);
	}
	
	public void changerVariante(Variante nouvelleVariante)
	{
		this.partieEnCours.setVarianteCourante(nouvelleVariante);
	}
	
	public int choisirVariante()
	{
		Scanner scanner = new Scanner(System.in);
		StringBuffer texte = new StringBuffer();
		int reponse;
		texte.append("Veuillez choisir une des variantes propos�es :\n");
		texte.append("1) Variante Minimale\n");
		texte.append("2) Variante Monclar\n");
		texte.append("3) Variante 4\n");
		texte.append("4) Variante 5\n");
		System.out.println(texte.toString());
		reponse = scanner.nextInt();
		while((reponse != 1 && reponse != 2 && reponse != 3 && reponse != 4) || (!Partie.estUnEntier(reponse + "")))
		{
			System.out.println("Vous devez choisir une des propositions (un nombre compris entre [1 et 4]). Veuillez resaisir une variante :");
			reponse = scanner.nextInt();
		}
		return(reponse);	
	}	
	
	//overridden methods	
	public int choisirAction()
	{
		Scanner scanner = new Scanner(System.in);
		StringBuffer texte = new StringBuffer();
		String reponse;
		texte.append(this.pseudo + ", veuillez choisir une action � ex�cuter :\n");
		texte.append("1) Jouer\n");
		texte.append("2) Piocher\n");
		texte.append("3) Changer de variante\n\n");		
		texte.append("             [Vous : " + this.main.size() + " cartes]\n");
		for(int x = 0 ; x < this.adversaires.size() ; x++)
		{
			Joueur adversaireX = this.adversaires.get(x);
			texte.append("             [" + adversaireX.getPseudo() + " : " + adversaireX.getMain().size() + " cartes]");
			if(this.joueurSuivant.equals(adversaireX))
				texte.append(" [joueur suivant]");
			texte.append("\n");
		}
		System.out.println(texte.toString());
		reponse = scanner.nextLine();
		boolean valide = false;
		while(!valide)
		{
			if(Partie.estUnEntier(reponse))
			{
				if(Integer.parseInt(reponse) == 1 || Integer.parseInt(reponse) == 2 || Integer.parseInt(reponse) == 3)
					valide = true;
			}
			else
			{
				valide = false;
				System.out.println("Vous devez choisir une des propositions (1, 2 ou 3). Veuillez resaisir une action � effectuer :");
				reponse = scanner.nextLine();
			}
		}
		return(Integer.parseInt(reponse));	
	}
	
	public Carte choisirCarte()
	{
		Scanner scanner = new Scanner(System.in);
		StringBuffer texte = new StringBuffer();
		String reponse = new String();
		texte.append("Veuillez choisir une carte � jouer :\n");
		for(int x = 0 ; x < this.main.size(); x++)
			texte.append((x+1) + ") " + ((Carte)this.main.toArray()[x]).toString() + "\n");
		System.out.println(texte.toString());
		reponse = scanner.nextLine();
		while(reponse.trim().equals(""))
		{
			while(reponse.trim().equals(""))
			{
				System.out.println("Vous devez saisir une valeur ! Allez-y :");
				reponse = new String(scanner.nextLine());
			}	
			while(!Partie.estUnEntier(reponse))
			{
				System.out.println("Vous devez entrez un nombre pour indiquer quelle carte choisir ! Allez-y :");
				reponse = new String(scanner.nextLine());
			}
			while(Integer.parseInt(reponse) > this.main.size() || Integer.parseInt(reponse) < 0)
			{	
				System.out.println("Vous devez s�lectionner un indice de carte propos� ! Veuillez saisir une carte :");
				reponse = new String(scanner.nextLine());
			}
		}
		return((Carte)(this.main.toArray()[Integer.parseInt(reponse) - 1]));
	}
	
	public Carte choisirCarteApresHuit()
	{
		Scanner scanner = new Scanner(System.in);
		StringBuffer texte = new StringBuffer();
		int reponse;
		Carte carte = null;
		texte.append("\nVeuillez choisir le symbole que vous souhaitez que l'on joue par la suite :\n");
		texte.append("1) Carreau\n");
		texte.append("2) Coeur\n");
		texte.append("3) Pique\n");
		texte.append("4) Tr�fle\n");
		System.out.println(texte.toString());
		reponse = scanner.nextInt();
		while(reponse > 4 && reponse < 1)
		{
			System.out.println("Vous devez choisir une r�ponse entre [1 et 4]. Veuillez resaisir votre r�ponse :");
			reponse = scanner.nextInt();
		}		
		switch(reponse)
		{
			case 1 : carte = new Carte(null, Symbole.CARREAU, null); break;
			case 2 : carte = new Carte(null, Symbole.COEUR, null); break;		
			case 3 : carte = new Carte(null, Symbole.PIQUE, null); break;
			case 4 : carte = new Carte(null, Symbole.TREFLE, null); break;
		}
		return(carte);
	}
	
	public String choisirCarteSupplement()
	{
		Scanner scanner = new Scanner(System.in);
		StringBuffer texte = new StringBuffer();
		String reponse = new String();
		texte.append("Veuillez choisir une carte en suppl�ment � jouer :\n");
		for(int x = 0 ; x < this.main.size(); x++)
			texte.append((x+1) + ") " + ((Carte)this.main.toArray()[x]).toString() + "\n");
		System.out.println(texte.toString());
		reponse = scanner.nextLine();
		if(!reponse.trim().equals(""))
		{
			while(!Partie.estUnEntier(reponse) || (Integer.parseInt(reponse) > this.main.size() || Integer.parseInt(reponse) < 0))
			{
				if(!reponse.trim().equals(""))
				{
					System.out.println("Vous devez choisir une des propositions de cartes ! Veuillez resaisir une carte :");
					reponse = new String(scanner.nextLine());
				}
				else
					break;
			}
		}
		if(reponse.trim().equals(""))
			return(reponse);
		else
			return(Integer.parseInt(reponse) - 1 + "");
	}
	
	public String proposerAjouterCarte()
	{
		Scanner scanner = new Scanner(System.in);
		StringBuffer texte = new StringBuffer();
		String reponse = new String();
		texte.append("\nSouhaitez-vous jouer une autre carte en suppl�ment ? [Y/N]\n");
		System.out.println(texte.toString());
		reponse = scanner.nextLine();
		while(!(reponse).equals("Y") && !(reponse).equals("N"))
		{
			System.out.println("Vous devez choisir entre [Y] et [N]. Veuillez resaisir votre r�ponse :\n");
			reponse = new String(scanner.nextLine());
		}
		return(reponse);
	}
}
