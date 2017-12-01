
class Order{
    private int vegieAmount;
    private int glutenFreeAmount;
    private int nutFreeAmount;
    private int fishFreeAmount;
    private int totalMeal;

    public Order(int vegieAmount, int glutenFreeAmount, int nutFreeAmount, int fishFreeAmount, int totalMeal){
        this.vegieAmount      = vegieAmount;
        this.glutenFreeAmount = glutenFreeAmount;
        this.nutFreeAmount    = nutFreeAmount;
        this.fishFreeAmount   = fishFreeAmount;
        this.totalMeal        = totalMeal;
    }

    public int getVegieAmount() {
        return vegieAmount;
    }
    public void setVegieAmount(int vegieAmount) {
        this.vegieAmount = vegieAmount;
    }
    public int getGlutenFreeAmount() {
        return glutenFreeAmount;
    }
    public void setGlutenFreeAmount(int glutenFreeAmount) {
        this.glutenFreeAmount = glutenFreeAmount;
    }
    public int getNutFreeAmount() {
        return nutFreeAmount;
    }
    public void setNutFreeAmount(int nutFreeAmount) {
        this.nutFreeAmount = nutFreeAmount;
    }
    public int getFishFreeAmount() {
        return fishFreeAmount;
    }
    public void setFishFreeAmount(int fishFreeAmount) {
        this.fishFreeAmount = fishFreeAmount;
    }
    public int getTotalMeal() {
        return totalMeal;
    }
    public void setTotalMeal(int totalMeal) {
        this.totalMeal = totalMeal;
    }
}
