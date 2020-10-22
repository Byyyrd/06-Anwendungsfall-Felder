package control;

/**
 * Created by Jean-Pierre on 20.10.2016.
 */
public class MainController {

    private CDCollectionHandler handler;

    public MainController(){
        handler = new CDCollectionHandler(new int[]{5,10,15,5});
        createDummyCDs();
    }

    private void createDummyCDs(){
        addNewCD(0,0,"ACDC","Highway to Hell");
        addNewCD(0,1,"ACDC","Back in Black");
        addNewCD(1,4,"Metallica","Master of Puppets");
        addNewCD(1,2,"Metallica","St. Anger");
        addNewCD(2,5,"Limp Bizkit","Significant Other");
        addNewCD(2,5,"Limp Bizkit","Chocolate Starfish and the Hot Dog Flavoured Water");
    }

    public String[] showAllCDs(int index){
        return handler.getAllCDsFrom(index);
    }

    public boolean addNewCD(int box, int place, String artist, String title){
        return handler.addNewCD(box, place, artist, title);
    }

    public boolean releaseCD(int box, int place){
        return handler.releaseCD(box, place);
    }

    public void packBox(int index){
        handler.pack(index);
    }

    public void sortBox(int index){
        handler.sort(index);
    }

    public String[] getSelectedCD(int box, int place){
        return handler.getInfo(box, place);
    }
}
