package DungeonCharacter;

public class Hero extends DungeonCharacter{
    protected int myOranBerryCount;
    //protected Item[] myInventory;

    Hero(String THE_NAME) {

        this.myOranBerryCount = 1;
//        this.myInventory = new Item[10];
//        for(int i = 0; i < this.myInventory.length; i++) {
//            myInventory[i] = null;
//        }
    }

    public String toString() {
        String result = "";
 //       result += "Name: " + this.myName + "\n";
 //       result += "HP: " + this.myHP + "\n";
        result += "Oran Berries: " + this.myOranBerryCount;
        //result += "Inventory: " + getInventory();
        return result;
    }

//    private String getInventory() {
//        String result = "";
//        for(int i = 0; i < this.myInventory.length; i++) {
//            if(this.myInventory[i] != null) {
//                result += this.myInventory[i].toString();
//            }
//        }
//        return result;
//    }
}
