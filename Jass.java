import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Diese Klasse ist die Hauptklasse und enthält die main-Methode
 * In der main-Methode soll eine neues (vollstänidges) Deck erzeugt
 * und gemischt werden. Dann sollen von diesem Deck 30 Karten entfernt
 * werden und anschliessend das EICHELN ASS hinzugefügt werden
 * Danach sollen alle Karten auf der Konsole ausgegeben werden.
 * 
 * EXTRA AUFGABEN:
 * Füge in der Klasse Deck eine öffentliche Instanzvariable von Typ Suit hinzu, 
 * welche für den Trumpf steht
 * 
 * Füge darauf in der Klasse Deck eine Methode validCards(Deck played) hinzu, 
 * welche aus this.cards die Karten zurückliefert, welche ausgespielt werden können, 
 * wenn zuvor die (maximal 3) Karten aus dem Deck played gespielt worden sind. 
 * 
 * Generiere in der Klasse Jass einen gültigen Schieber-Jass zwischen 4 Computerspieler, 
 * welche jeweils gültige Karten ausspielen 
 * Erlaube dem Benutzer mitzuspielen. 
 * 
 * Die Karten in seiner Hand sollen jeweils angezeigt werden. Der Spieler soll per 
 * Konsole eingeben, welche Karte er ausspielen möchte. Das Programm darf nur gültige 
 * Karten als Eingabe akzeptieren.
 */
public class Jass {
    public static Card stringToCard(String string) throws Exception{
        String[] strAsArray = string.toUpperCase().split("[\s]");
        Suit suit;
        Rank rank;

        switch (strAsArray[0].strip()) {
            case "SCHELLEN":
                suit = Suit.SCHELLEN;
                break;
            case "SCHILTEN":
                suit = Suit.SCHILTEN;
                break;
            case "ROSEN":
                suit = Suit.ROSEN;
                break;
            case "EICHELN":
                suit = Suit.EICHELN;
                break;
            default:
                throw new Exception("Invalid Suit");
        }

        switch (strAsArray[1].strip()) {
            case "SECHS":
                rank = Rank.SECHS;
                break;
            case "SIEBEN":
                rank = Rank.SIEBEN;
                break;
            case "ACHT":
                rank = Rank.ACHT;
                break;
            case "NEUN":
                rank = Rank.NEUN;
                break;
            case "BANNER":
                rank = Rank.BANNER;
                break;
            case "UNDER":
                rank = Rank.UNDER;
                break;
            case "OBER":
                rank = Rank.OBER;
                break;
            case "KOENIG":
                rank = Rank.KOENIG;
                break;
            case "ASS":
                rank = Rank.ASS;
                break;
            default:
                throw new Exception("Invalid Rank");
        }

        return new Card(suit, rank);
    }

    public static int indexOf(Deck deckToSearch, Card cardToFind) throws Exception {
        for (int i = 0; i < deckToSearch.getCards().length; i++) {
            if (cardToFind.equals(deckToSearch.getCards()[i])) {
                return i;
            }
        }
        throw new Exception("The card " + cardToFind + " could not be found.");
    }

    public static int[][] assignOrderAndValue(Deck deck) {
        int[][] out = new int[deck.getCards().length][2];
        for (int i = 0; i < out.length; i++) {
            if (deck.getCards()[i].getSuit() != deck.trumpf) {
                switch (deck.getCards()[i].getRank()) {
                    case Rank.SIEBEN:
                        out[i][0] = 2; // Order, highest in played deck wins
                        out[i][1] = 0; // Value, winner gets summed values
                        break;
                    case Rank.ACHT:
                        out[i][0] = 3; 
                        out[i][1] = 0;
                        break;
                    case Rank.NEUN:
                        out[i][0] = 4; 
                        out[i][1] = 0;
                        break;
                    case Rank.BANNER:
                        out[i][0] = 5; 
                        out[i][1] = 10;
                        break;
                    case Rank.UNDER:
                        out[i][0] = 6; 
                        out[i][1] = 2;
                        break;
                    case Rank.OBER:
                        out[i][0] = 7; 
                        out[i][1] = 3;
                        break;
                    case Rank.KOENIG:
                        out[i][0] = 8; 
                        out[i][1] = 4;
                        break;
                    case Rank.ASS:
                        out[i][0] = 9; 
                        out[i][1] = 11;
                        break;
                    default:
                        out[i][0] = 1;
                        out[i][1] = 0;
                } 
            } else { // If it's the trumpf color
                    switch (deck.getCards()[i].getRank()) {
                        case Rank.SIEBEN:
                            out[i][0] = 11;
                            out[i][1] = 0;
                            break;
                        case Rank.ACHT:
                            out[i][0] = 12; 
                            out[i][1] = 0;
                            break;
                        case Rank.BANNER:
                            out[i][0] = 13; 
                            out[i][1] = 10;
                            break;
                        case Rank.OBER:
                            out[i][0] = 14; 
                            out[i][1] = 3;
                            break;
                        case Rank.KOENIG:
                            out[i][0] = 15; 
                            out[i][1] = 4;
                            break;
                        case Rank.ASS:
                            out[i][0] = 16; 
                            out[i][1] = 11;
                            break;
                        case Rank.NEUN:
                            out[i][0] = 17; 
                            out[i][1] = 14;
                            break;
                        case Rank.UNDER:
                            out[i][0] = 18; 
                            out[i][1] = 20;
                            break;
                        default:
                            out[i][0] = 10;
                            out[i][1] = 0;
                }
            }
        }
        return out;
    }

    public static void payOut(Deck deck, int[] pointsArray) {}

    public static Deck createHand(Deck deck) {
        Deck retDeck = new Deck(new Card[0], Suit.EICHELN);
        int bound = deck.getCards().length;
        for (int i = bound; i > bound - 9; i--) {
            try {
                retDeck.addCard(deck.getCards()[i - 1]);
                deck.pop();
            }
            catch (Exception e) {
                System.err.println(e);
            }
        }
        return retDeck;
    }

    public static void botPlay(Deck hand, Deck played) {
        Random rng = new Random();
        int handIndex = hand.getCards().length - 1;
        Card cardToPlay;
        if (played.getCards().length == 0) {
            handIndex = rng.nextInt(hand.getCards().length);
            cardToPlay = hand.getCards()[handIndex];
        } else {
            Card[] validCards = hand.validCards(played);
            Card[] trumpfCards = new Card[0];
            for (Card card : validCards) {
                if (card.getSuit() == played.trumpf) {
                    trumpfCards = Arrays.copyOf(trumpfCards, trumpfCards.length + 1);
                    trumpfCards[trumpfCards.length - 1] = card;
                }
            }
            if (trumpfCards.length > 0) {
                handIndex = rng.nextInt(trumpfCards.length);
                cardToPlay = trumpfCards[handIndex];
            } else {
                handIndex = rng.nextInt(validCards.length);
                cardToPlay = validCards[handIndex];
            }
        }
        try {
            played.addCard(cardToPlay);
            System.out.println("Bot spielte " + cardToPlay + ".");
            hand.swapCardToTop(indexOf(hand, cardToPlay));
            hand.pop();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /*
     * playerPlay Method assumes that cardToPlay was checked before being passed
     * -Why? 
     * -Do not question my methods
     */
    public static void playerPlay(Card cardToPlay, Deck playerDeck, Deck played) {
        for (int i = playerDeck.getCards().length - 1; i >= 0; i--) {
            if (playerDeck.getCards()[i].equals(cardToPlay)) {
            try {
                played.addCard(cardToPlay);
                playerDeck.swapCardToTop(i);
                playerDeck.pop();
            } catch (Exception e) {
                System.err.println(e);
            }
            }
        }
    }

    public static void main(String[] args) {
        Random randomizer = new Random();
        Deck deck = new Deck();
        Scanner inputScanner = new Scanner(System.in); // handles console input

        System.out.print("Geben sie 'Y' ein um Spieler 1 zu werden: ");

        String uResponse = inputScanner.nextLine();

        boolean userPlaying = uResponse.toUpperCase().strip().equals("Y");

        deck.shuffle();

        System.out.println("Initial Deck: \n" + deck);

        Deck p1Hand = createHand(deck);
        int p1Points = 0;

        Deck p2Hand = createHand(deck);
        int p2Points = 0;

        Deck p3Hand = createHand(deck);
        int p3Points = 0;

        Deck p4Hand = createHand(deck);
        int p4Points = 0;

        deck.trumpf = Suit.values()[randomizer.nextInt(4)];

        System.out.println("Spieler 1 Hand:\n" + p1Hand);

        System.out.println("Spieler 2 Hand:\n" + p2Hand);

        System.out.println("Spieler 3 Hand:\n" + p3Hand);

        System.out.println("Spieler 4 Hand:\n" + p4Hand);

        System.out.println("Gespielt: " + deck);

        System.out.println("Trumpf: " + deck.trumpf);

        int[][] ordValArray = new int[0][0];

        for (int i = 0; i < 9; i++) {
            System.out.print("1, ");
            if (userPlaying) {
                System.out.println("Spieler 1 Hand: " + p1Hand);
                Card[] valid = p1Hand.validCards(deck);
                System.out.println("Spieler 1 spielbare Karten: " + new Deck(valid, Suit.EICHELN));
                wloop:
                while (true) {
                    System.out.println("Geben sie eine Karte (z.B. 'schellen acht') ein (oder q):");
                    uResponse = inputScanner.nextLine().toUpperCase();
                    if (uResponse.equals("Q")) {
                        inputScanner.close();
                        return;
                    }
                    for (Card c : valid) {
                        try {
                            if (c.equals(stringToCard(uResponse))) {
                                playerPlay(c, p1Hand, deck);
                                break wloop;
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }
            } else {
                botPlay(p1Hand, deck);
            }
            System.out.print("2, ");
            botPlay(p2Hand, deck);
            System.out.print("3, ");
            botPlay(p3Hand, deck);
            System.out.print("4, ");
            botPlay(p4Hand, deck);
            System.out.println("Gespielt: " + deck);

            ordValArray = assignOrderAndValue(deck);

            int highestOrd = 0;

            int valSum = ordValArray[0][1];

            for (int j = 1; j < ordValArray.length; j++) {
                valSum += ordValArray[j][1];
                if (ordValArray[j][0] > ordValArray[highestOrd][0]) {
                    highestOrd = j;
                }
            }

            switch (highestOrd) {
                case 1:
                    p2Points += valSum;
                    System.out.println("Spieler 2 gewinnt!");
                    break;
                case 2:
                    p3Points += valSum;
                    System.out.println("Spieler 3 gewinnt!");
                    break;
                case 3:
                    p4Points += valSum;
                    System.out.println("Spieler 4 gewinnt!");
                    break;
                default:
                    p1Points += valSum;
                    System.out.println("Spieler 1 gewinnt!");
                    break;
            }

            try {
                deck.multiPop(4);
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println("Spieler 1 hat " + p1Points + " Punkte.");
        System.out.println("Spieler 2 hat " + p2Points + " Punkte.");
        System.out.println("Spieler 3 hat " + p3Points + " Punkte.");
        System.out.println("Spieler 4 hat " + p4Points + " Punkte.");
        inputScanner.close();
    }
}
