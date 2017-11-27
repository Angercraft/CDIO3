package juniorMatador;

import java.awt.*;

public class ChanceController {

    ChanceBase[] cards = new ChanceBase[15];

    ChanceController() {
        createCards();
    }

    public void createCards() {
        cards[0] = new ChanceStart("Gå til 'Start'. Modtag 9 kr.", "START", 9);
        cards[1] = new ChanceMoveTo("Ryk 5 felter frem.", "MOVETO", 5);
        cards[2] = new ChanceFreeField("Ryk frem til et orange felt. Hvis det ledigt er det gratis, ellers betal lejen.", "FREEFIELD", Color.ORANGE);
        cards[3] = new ChanceMoveTo("Ryk 1 felt frem.", "MOVETO", 1);
        cards[4] = new ChanceMoney("Du har spist for meget slik. Betal 2 kr. til banken.", "MONEY", 2, true, false);
        cards[5] = new ChanceFreeField("Ryk frem til et orange eller grønt felt. Hvis det ledigt er det gratis, ellers betal lejen.", "FREEFIELD", Color.ORANGE, Color.GREEN);
        cards[6] = new ChanceFreeField("Ryk frem til et orange felt. Hvis det ledigt er det gratis, ellers betal lejen.", "FREEFIELD", Color.BLUE.brighter());
        cards[7] = new ChanceSkipJail("Næste gang du du rammer 'Gå i fængsel' feltet, bliver du på feltet og slipper for at betale", "SKIPJAIL");
        cards[8] = new ChanceMoveTo("Ryk frem til Strandpromenaden.", "MOVETO", 24);
        cards[9] = new ChanceMoney("Det er din fødselsdag. Alle giver dig 1 kr.", "MONEY", 1, false, true);
        cards[10] = new ChanceFreeField("Ryk frem til et pink eller mørke blåt felt. Hvis det ledigt er det gratis, ellers betal lejen.", "FREEFIELD", Color.PINK, Color.BLUE.darker());
        cards[11] = new ChanceMoney("Du har lavet alle dine lektier. Modtag 2 kr.", "MONEY", 2);
        cards[12] = new ChanceFreeField("Ryk frem til et rødt felt. Hvis det ledigt er det gratis, ellers betal lejen.", "FREEFIELD", Color.RED);
        cards[13] = new ChanceFreeField("Ryk frem til et lyse blåt eller rødt felt. Hvis det ledigt er det gratis, ellers betal lejen.", "FREEFIELD", Color.BLUE.brighter(), Color.RED);
        cards[14] = new ChanceFreeField("Ryk frem til et pink eller gult felt. Hvis det ledigt er det gratis, ellers betal lejen.", "FREEFIELD", Color.PINK, Color.YELLOW);
    }

    public ChanceBase getCard(int number) {
        return cards[number];
    }
}
