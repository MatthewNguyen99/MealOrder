
class Restaurant{
    private String name;
    private int rating;
    private int maxMealPerday;
    private Order maxOrder;

    Restaurant(String name, int rating, int maxMealPerday, Order maxOrder){
        this.name           = name;
        this.rating         = rating;
        this.maxMealPerday  = maxMealPerday;
        this.maxOrder       = maxOrder;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public int getMaxMealPerday() {
        return maxMealPerday;
    }
    public void setMaxMealPerday(int maxMealPerday) {
        this.maxMealPerday = maxMealPerday;
    }
    public Order getOrder() {
        return maxOrder;
    }
    public void setOrder(Order maxOrder) {
        this.maxOrder = maxOrder;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + " (");
        if(maxOrder.getVegieAmount()      != 0)  stringBuilder.append(maxOrder.getVegieAmount() + " vegetarian + ");
        if(maxOrder.getGlutenFreeAmount() != 0)  stringBuilder.append(maxOrder.getGlutenFreeAmount() + " gluten free + ");
        if(maxOrder.getNutFreeAmount()    != 0)  stringBuilder.append(maxOrder.getNutFreeAmount() + " nut free + ");
        if(maxOrder.getFishFreeAmount()   != 0)  stringBuilder.append(maxOrder.getFishFreeAmount() + " fish free + ");
        stringBuilder.append(maxOrder.getTotalMeal() + " others)");

        return stringBuilder.toString();
    }
}
