/**
 * Diese Klasse repräsentiert eine Karte (Bsp. EICHELN ASS)
 * Sie soll Instanzvariablen von Typ "Suit" und "Rank" haben,
 * einen Konstruktor, eine Methode "toString()" zur Representation
 * als Zeichenkette, sowie eine "equals(Card other)" Methode, welche
 * zurückgibt, ob die aktuelle Karte gleichen Suit und Rank wie die
 * andere Karte hat.
 */
public class Card {
    private Suit suit;
    private Rank rank;
    
    public Card(Suit suit, Rank rank) {
        this.setSuit(suit);
        this.setRank(rank);
    }

    public Card(Card card) {
        this(card.getSuit(), card.getRank());
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public void setValues(Suit suit, Rank rank) {
        this.setSuit(suit);
        this.setRank(rank);
    }

    public void setValues(Card card) {
        this.setSuit(card.getSuit());
        this.setRank(card.getRank());
    }

    public int getFaceValue(boolean ifTrumpf) {
        int retVal = 0;
        switch (this.getRank()) {
            case Rank.SECHS:
                retVal = 0;
                break;
            case Rank.SIEBEN:
                retVal = 0;
                break;
            case Rank.ACHT:
                retVal = 0;
                break;
            case Rank.NEUN:
                retVal = (ifTrumpf) ? 14 : 0;
                break;
            case Rank.BANNER:
                retVal = 10;
                break;
            case Rank.UNDER:
                retVal = (ifTrumpf) ? 20 : 2;
                break;
            case Rank.OBER:
                retVal = 3;
                break;
            case Rank.KOENIG:
                retVal = 4;
                break;
            case Rank.ASS:
                retVal = 11;
                break;
        }
        return retVal;
    }

    public boolean equals(Card other) {
        return this.getSuit() == other.getSuit() && this.getRank() == other.getRank();
    }

    @Override
    public String toString() {
        return "[" + this.getSuit() + " " + this.getRank() + "]";
    }
}
