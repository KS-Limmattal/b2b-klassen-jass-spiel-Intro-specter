import java.util.Random;
import java.util.Arrays;

/**
 * Diese Klasse repräsentiert einen Kartenstapel mit einer variablen Anzahl
 * Karten
 * Sie soll ein Array von Typ Card als Instanzvariable haben,
 * - einen Konstruktor Deck(Card[] cards), welches eine Instanz bestehend aus
 * den gegebenen Karten kreiert,
 * - einen Konstruktor Deck(), welcher ein vollständiges Kartenset (4x9 Karten)
 * erzeugt,
 * - einen (trivialen) Getter getCards()
 * - eine Methode addCard(Card card), welche zum Deck eine Karte hinzufügt,
 * falls diese noch nicht im Deck enthalten ist und andernfalls eine Warnung auf
 * der Konsole ausgibt
 * - eine Methode pop(), welche die letzte Karte im Array aus dem Deck entfernt,
 * sofern Karten vorhanden sind
 * - eine Methode shuffle(), welche die Karten im Array durchmischt
 * 
 * Tipps:
 * - Um ein Array zu redimensionieren, verwende den Befehl "Arrays.copyOf" aus
 * java.util.Arrays
 * - Um eine zufällige Ganzzahl in einem gegebenen Bereich zu erzeugen, verwende
 * "rnd.nextInt", wobei "rnd" eine Instanz der Klasse "Random" aus
 * "java.util.Random" bezeichnet
 *
 */
public class Deck {
    private Card[] cards;
    private Random rnd = new Random();
    public Suit trumpf;

    public Deck(Card[] cards, Suit trumpf) {
        this.setCards(cards);
        this.trumpf = trumpf;
    }

    public Deck() {
        this(generateCards(), Suit.values()[0]);
        this.trumpf = Suit.values()[rnd.nextInt(4)];
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public static Card[] generateCards() {
        Card[] newCards = new Card[36];
        int i = 0;
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                newCards[i] = new Card(suit, rank);
                i++;
            }
        }
        return newCards;
    }

    public void addCard(Card card) throws Exception {
        for (Card i : this.getCards()) {
            if (i.equals(card)) {
                throw new Exception("Die Karte " + card + " ist schon enthalten.");
            }
        }
        Card[] newCards = Arrays.copyOf(this.getCards(), this.getCards().length + 1);
        newCards[newCards.length - 1] = card;
        this.setCards(newCards);
    }

    public void pop() throws Exception {
        if (this.getCards().length > 0) {
            this.setCards(Arrays.copyOf(this.getCards(), this.getCards().length - 1));
        } else {
            throw new Exception("Keine Karten zu poppen.");
        }
    }

    public void multiPop(int n) throws Exception {
        for (int i = 0; i < n; i++) {
            this.pop();
        }
    }

    /*
    * Make array of indices of cards
    * randint an index and index into temp array
    * swap element to the end then index into cards and copy to newcards
    */
    public void shuffle() {

        int[] indexArray = new int[this.getCards().length];
        for (int i = 0; i < this.getCards().length; i++) {
            indexArray[i] = i;
        }

        Card[] newCards = Arrays.copyOf(this.getCards(), this.getCards().length);

        for (int i = indexArray.length; i > 0; i--) {
            int randomNumber = rnd.nextInt(i);
            int choice = indexArray[randomNumber];
            int temp = indexArray[i - 1];
            indexArray[i - 1] = choice;
            indexArray[randomNumber] = temp;
            newCards[i - 1] = this.getCards()[choice];
        }

        this.setCards(newCards);
    }

    public void swapCardToTop(int index) {
        Card[] newCards = Arrays.copyOf(this.getCards(), this.getCards().length);
        newCards[this.getCards().length - 1] = this.getCards()[index];
        newCards[index] = this.getCards()[this.getCards().length - 1];
        this.setCards(newCards);
    }

    public Card[] validCards(Deck played) {
        if (played.getCards().length < 1) {
            return this.getCards();
        }
        Suit firstSuit = played.getCards()[0].getSuit();
        boolean containsFirstSuit = false;
        Card[] retCards = new Card[0];

        for (Card card : this.getCards()) {
            if (card.getSuit() == firstSuit) {
                containsFirstSuit = true;
                retCards = Arrays.copyOf(retCards, retCards.length + 1);
                retCards[retCards.length - 1] = card;
            }
        }

        if (!containsFirstSuit) {
            retCards = this.getCards();
        }

        return retCards;
    }

    @Override
    public String toString() {
        String str = "{ ";
        for (Card i : this.getCards()) {
            str += i + " ";
        }
        return str + "}";
    }
}
