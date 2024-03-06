import java.util.ArrayList;

/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael KÃ¶lling.
 * @version 2011.07.31
 */
public class Auction
{
    // The list of Lots in this auction.
    private ArrayList<Lot> lots;
    // The number that will be given to the next lot entered
    // into this auction.
    private int nextLotNumber;

    /**
     * Create a new auction.
     */
    public Auction()
    {
        lots = new ArrayList<Lot>();
        nextLotNumber = 1;
    }

    /**
     * Enter a new lot into the auction.
     * @param description A description of the lot.
     */
    public void enterLot(String description)
    {
        lots.add(new Lot(nextLotNumber++, description));
    }

    /**
     * Show the full list of lots in this auction.
     */
    public void showLots()
    {
        for(Lot lot : lots) {
            System.out.println(lot.toString());
        }
    }
    
    /**
     * Make a bid for a lot.
     * A message is printed indicating whether the bid is
     * successful or not.
     * 
     * @param lotNumber The lot being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public void makeABid(int lotNumber, Person bidder, long value)
    {
        Lot selectedLot = getLot(lotNumber);
        if(selectedLot != null) {
            Bid bid = new Bid(bidder, value);
            boolean successful = selectedLot.bidFor(bid);
            if(successful) {
                System.out.println("The bid for lot number " +
                                   lotNumber + " was successful.");
            }
            else {
                // Report which bid is higher.
                System.out.println("Lot number: " + lotNumber +
                                   " already has a bid of: " +
                                   selectedLot.getHighestBid().getValue());
            }
        }
    }

    /**
     * Return the lot with the given number. Return null
     * if a lot with this number does not exist.
     * @param lotNumber The number of the lot to return.
     */
    public Lot getLot(int lotNumber)
    {
        if((lotNumber >= 1) && (lotNumber < nextLotNumber)) {
            // The number seems to be reasonable.
            Lot selectedLot = lots.get(lotNumber - 1);
            // Include a confidence check to be sure we have the
            // right lot.
            if(selectedLot.getNumber() != lotNumber) {
                System.out.println("Internal error: Lot number " +
                                   selectedLot.getNumber() +
                                   " was returned instead of " +
                                   lotNumber);
                // Don't return an invalid lot.
                selectedLot = null;
            }
            return selectedLot;
        }
        else {
            System.out.println("Lot number: " + lotNumber +
                               " does not exist.");
            return null;
        }
    }
    
    public void close() {
        for (Lot lot : lots) {
            System.out.print(lot.getNumber() + ". " + lot.getDescription() + ": ");
            if (lot.getHighestBid() != null) {
                System.out.println("Bid: " + lot.getHighestBid().getValue());
                System.out.println("Nombre de la persona que ha pujado más alto: " + lot.getHighestBid().getBidder().getName());
                System.out.println("El valor de la puja que realizó es de: " + lot.getHighestBid().getValue());
            } else {
                System.out.println("(no bid)");
            }
        }
    }
    
    public ArrayList<Lot> getUnsold() {
        ArrayList<Lot> unsoldLots = new ArrayList<>();
        for (Lot lot : lots) {
            if (lot.getHighestBid() == null) {
                unsoldLots.add(lot);
            }
        }
        return unsoldLots;
    }
    
    /** 
     * Elimina el lote con el número de lote especificado.
     * @param number El número del lote que hay que eliminar,
     * @return El lote con el número dado o null si no existe tal lote.
     */
    public Lot removeLot(int number) {
        Lot lotToRemove = getLot(number);
        if (lotToRemove != null) {
            lots.remove(lotToRemove);
        } 
        else {
            System.out.println("El numero de Lot: " + number + " no existe");
        }
        return lotToRemove;
    }
}