
public class MealOrder {

    static int totalOrderMadeByA = 0;
    static int totalOrderMadeByB = 0;
    static int orderTotalMeal = 0;
    /**
     * Scan the inputs from customers. Customers enter the values of each order
     * If customers NEGATIVE number or a letter, show NOT ACCEPTED and 0 order.
     *
     * @param vegieAmount      : the amount of vegetarian order
     * @param glutenFreeAmount : the amount of gluten free order
     * @param nutFreeAmount    : the amount of nut free order
     * @param fishFreeAmount   : the amount of fish free order
     * @param totalMeal        : the total amount
     * @return the object Order which contains all values
     */
    public Order scanInput(int vegieAmount,int glutenFreeAmount,int nutFreeAmount,int fishFreeAmount,int totalMeal ){
        System.out.print("\nEnter custom meal order \n");
        System.out.print("1. Vegie Amount: ");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()){
            try {
                vegieAmount = scanner.nextInt();

                System.out.print("2. Gluten Free Amount: ");
                glutenFreeAmount = scanner.nextInt();

                System.out.print("3. Nut Free Amount: ");
                nutFreeAmount = scanner.nextInt();

                System.out.print("4. Fish Free Amount: ");
                fishFreeAmount = scanner.nextInt();

                System.out.print("5. Total Meal: ");
                totalMeal = scanner.nextInt();

                // if any input is negative, then set all to 0 (zero)
                if (vegieAmount < 0 || glutenFreeAmount < 0 || nutFreeAmount < 0 || fishFreeAmount < 0 || totalMeal < 0) {
                    System.out.println("Negative input not accepted ");
                    vegieAmount      = 0;
                    glutenFreeAmount = 0;
                    nutFreeAmount    = 0;
                    fishFreeAmount   = 0;
                    totalMeal        = 0;
                    break;
                }

                // check if the total is less than the whole sum, then total = the whole sum
                if (totalMeal < (vegieAmount + glutenFreeAmount + nutFreeAmount + fishFreeAmount)) {
                    totalMeal = (vegieAmount + glutenFreeAmount + nutFreeAmount + fishFreeAmount);
                    System.out.println("Total cannot be less than the whole order. Total = " + totalMeal);
                    break;
                }
                System.out.print("\n\n ");
                break;
            }
            catch (InputMismatchException e){
                System.out.println("Invalid input not accepted ");
            }
            catch (NumberFormatException e){
                System.out.println("Invalid input not accepted ");
            }
        }
        return new Order(vegieAmount, glutenFreeAmount, nutFreeAmount, fishFreeAmount, totalMeal);
    }
    /**
     * First: place the order on higher rating restaurant. If the order reaches the maximum capacity of higher
     * rating restaurant, the remaining of the order will be placed on lower rating restaurant.
     *
     * Higher Rating Restaurant and Lower Rating Restaurant are checked in the main method.
     *
     * Check the condition of each restaurant. If the restaurant has 0 (zero) in any field, that restaurant
     * does not accept that order by default
     * Eg. Restaurant A accepts only Vegie, so Vegie = 4.  The nut, gluten, fish are not accepted, then = 0
     *
     * @param myOrder             : the order for the team
     * @param highRatingRestaurant: the restaurant with higher rating, this is restaurant A rating 5
     * @param lowRatingRestaurant : the restaurant with lower rating, this is restaurant B rating 3
     */
    public void placeOrder(Order myOrder, Restaurant highRatingRestaurant, Restaurant lowRatingRestaurant){
        // order for the team
        ArrayList<Integer> arrOrderAmount = new ArrayList<>();
        int orderVegieAmount      = myOrder.getVegieAmount();
        int orderGlutenFreeAmount = myOrder.getGlutenFreeAmount();
        int orderNutFreeAmount    = myOrder.getNutFreeAmount();
        int orderFishFreeAmount   = myOrder.getFishFreeAmount();
        orderTotalMeal        = myOrder.getTotalMeal();
        arrOrderAmount.add(orderVegieAmount);
        arrOrderAmount.add(orderGlutenFreeAmount);
        arrOrderAmount.add(orderNutFreeAmount);
        arrOrderAmount.add(orderFishFreeAmount);

        // max amount handled by highRatingRestaurant, aka Restaurant A
        ArrayList<Integer> arrMaxARestAmout = new ArrayList<>();
        int maxRestAVegieAmount      = highRatingRestaurant.getOrder().getVegieAmount();
        int  maxRestAGlutenFreeAmount = highRatingRestaurant.getOrder().getGlutenFreeAmount();
        int  maxRestANutFreeAmount    = highRatingRestaurant.getOrder().getNutFreeAmount();
        int maxRestAFishFreeAmount   = highRatingRestaurant.getOrder().getFishFreeAmount();
        int  maxRestATotalMeal        = highRatingRestaurant.getMaxMealPerday();
        arrMaxARestAmout.add(maxRestAVegieAmount);
        arrMaxARestAmout.add(maxRestAGlutenFreeAmount);
        arrMaxARestAmout.add(maxRestANutFreeAmount);
        arrMaxARestAmout.add(maxRestAFishFreeAmount);

        // max amount handled by lowRatingRestaurant, aka Restaurant B
        ArrayList<Integer> arrMaxBRestAmout = new ArrayList<>();
        int maxRestBVegieAmount      = lowRatingRestaurant.getOrder().getVegieAmount();
        int maxRestBGlutenFreeAmount = lowRatingRestaurant.getOrder().getGlutenFreeAmount();
        int maxRestBNutFreeAmount    = lowRatingRestaurant.getOrder().getNutFreeAmount();
        int maxRestBFishFreeAmount   = lowRatingRestaurant.getOrder().getFishFreeAmount();
        int maxRestBTotalMeal        = lowRatingRestaurant.getMaxMealPerday();
        arrMaxBRestAmout.add(maxRestBVegieAmount);
        arrMaxBRestAmout.add(maxRestBGlutenFreeAmount);
        arrMaxBRestAmout.add(maxRestBNutFreeAmount);
        arrMaxBRestAmout.add(maxRestBFishFreeAmount);

        arrangeOrder(arrOrderAmount,arrMaxARestAmout,arrMaxBRestAmout,highRatingRestaurant, lowRatingRestaurant);

        /*/ get the available amount restaurant A and restaurant B can still handle*/
        int leftOverForRestA = maxRestATotalMeal - totalOrderMadeByA;
        int leftOverForRestB = maxRestBTotalMeal - totalOrderMadeByB;

        // set the availability to each restaurant
        if( leftOverForRestA < orderTotalMeal){
            highRatingRestaurant.getOrder().setTotalMeal(leftOverForRestA);

            int remainingForB = orderTotalMeal - leftOverForRestA;
            if(maxRestBTotalMeal < remainingForB){
                if(leftOverForRestB < maxRestBTotalMeal ) {
                    lowRatingRestaurant.getOrder().setTotalMeal(leftOverForRestB);
                }
                else {
                    lowRatingRestaurant.getOrder().setTotalMeal(maxRestBTotalMeal);
                }
            }
            else if(maxRestBTotalMeal > remainingForB){
                lowRatingRestaurant.getOrder().setTotalMeal(remainingForB);
            }
        }
        else{
            lowRatingRestaurant.getOrder().setTotalMeal(0);
            highRatingRestaurant.getOrder().setTotalMeal(orderTotalMeal);
        }
    }
    /*
     * Arrange the meal order.  Each restaurant has 4 limitations
     * This method calculates and updates the total amount.
     * 
     */
    public void arrangeOrder(ArrayList arrOrderAmount,ArrayList arrMaxARestAmout,ArrayList arrMaxBRestAmout,
                             Restaurant highRatingRestaurant, Restaurant lowRatingRestaurant){
        int fourKindsOrders = 4;
        for(int i = 0; i<fourKindsOrders; i++){
            int orderAmount    = (int)arrOrderAmount.get(i);
            int maxRestAamount = (int)arrMaxARestAmout.get(i);
            int maxRestBamount = (int)arrMaxBRestAmout.get(i);

            if(maxRestAamount < orderAmount){
                orderTotalMeal -= maxRestAamount;
                if(maxRestAamount != 0){
                    totalOrderMadeByA += maxRestAamount;
                    if (i==0)  highRatingRestaurant.getOrder().setVegieAmount(maxRestAamount);
                    else if (i==1)  highRatingRestaurant.getOrder().setGlutenFreeAmount(maxRestAamount);
                    else if (i==2)  highRatingRestaurant.getOrder().setNutFreeAmount(maxRestAamount);
                    else if (i==3)  highRatingRestaurant.getOrder().setFishFreeAmount(maxRestAamount);
                }
                int remainingForB = orderAmount - maxRestAamount;
                if(maxRestBamount != 0 && maxRestBamount <= remainingForB){
                    orderTotalMeal -= maxRestBamount;
                    totalOrderMadeByB += maxRestBamount;
                    if (i==0)  lowRatingRestaurant.getOrder().setVegieAmount(maxRestBamount);
                    else if (i==1)  lowRatingRestaurant.getOrder().setGlutenFreeAmount(maxRestBamount);
                    else if (i==2)  lowRatingRestaurant.getOrder().setNutFreeAmount(maxRestBamount);
                    else if (i==3)  lowRatingRestaurant.getOrder().setFishFreeAmount(maxRestBamount);
                }
                else if(maxRestBamount >= remainingForB){
                    orderTotalMeal -= remainingForB;
                    totalOrderMadeByB += remainingForB;
                    if (i==0)  lowRatingRestaurant.getOrder().setVegieAmount(remainingForB);
                    else if (i==1)  lowRatingRestaurant.getOrder().setGlutenFreeAmount(remainingForB);
                    else if (i==2)  lowRatingRestaurant.getOrder().setNutFreeAmount(remainingForB);
                    else if (i==3)  lowRatingRestaurant.getOrder().setFishFreeAmount(remainingForB);
                }
            }
            else{
                orderTotalMeal -= orderAmount;
                if (i==0)  lowRatingRestaurant.getOrder().setVegieAmount(0);
                else if (i==1)  lowRatingRestaurant.getOrder().setGlutenFreeAmount(0);
                else if (i==2)  lowRatingRestaurant.getOrder().setNutFreeAmount(0);
                else if (i==3)  lowRatingRestaurant.getOrder().setFishFreeAmount(0);
                totalOrderMadeByA += orderAmount;
                if (i==0)  highRatingRestaurant.getOrder().setVegieAmount(orderAmount);
                else if (i==1)  highRatingRestaurant.getOrder().setGlutenFreeAmount(orderAmount);
                else if (i==2)  highRatingRestaurant.getOrder().setNutFreeAmount(orderAmount);
                else if (i==3)  highRatingRestaurant.getOrder().setFishFreeAmount(orderAmount);
            }
        }
    }

    /**
     * Print the recommendation of the order
     *
     * @param arrListRestaurant      : list of the restaurants
     */
    public void printRecommendation(ArrayList<Restaurant> arrListRestaurant){
        System.out.print("\nExpected meal orders: \n");
        for(Restaurant restaurant: arrListRestaurant){
            System.out.println(restaurant);
        }
    }

    public static void main(String [] args){
        //0. This is the default preset order. Customer can modify this order by enter new value
        int vegieAmount      = 0;
        int glutenFreeAmount = 0;
        int nutFreeAmount    = 0;
        int fishFreeAmount   = 0;
        int totalMeal        = 0;
        //Order order = new Order(vegieAmount, glutenFreeAmount, nutFreeAmount, fishFreeAmount, totalMeal);

        //1. Set the condition for restaurant A
        String nameRestA   = "Restaurant A";
        int ratingA        = 5;
        int vegieMaxA      = 4;
        int glutenFreeMaxA = 0;
        int nutFreeMaxA    = 10;
        int fishFreeMaxA   = 0;
        int maxMealPerdayA = 40;
        Order maxOrderA  = new Order(vegieMaxA, glutenFreeMaxA, nutFreeMaxA, fishFreeMaxA, maxMealPerdayA);
        Restaurant restA = new Restaurant(nameRestA, ratingA, maxMealPerdayA, maxOrderA);

        //2. Set the condition for restaurant B
        String nameRestB   = "Restaurant B";
        int ratingB        = 3;
        int vegieMaxB      = 20;
        int glutenFreeMaxB = 20;
        int nutFreeMaxB    = 0;
        int fishFreeMaxB   = 0;
        int maxMealPerdayB = 100;
        Order maxOrderB  = new Order(vegieMaxB, glutenFreeMaxB, nutFreeMaxB, fishFreeMaxB, maxMealPerdayB);
        Restaurant restB = new Restaurant(nameRestB, ratingB, maxMealPerdayB, maxOrderB);

        //4. Find rating of restaurants
        Restaurant highRatingRestaurant   = (restA.getRating() > restB.getRating())?  restA : restB; //RestA
        Restaurant lowRatingRestaurant    = (highRatingRestaurant.getName().equals("Restaurant A"))? restB: restA; //RestB

        //7. Scan input for custom order
        MealOrder mealOrder = new MealOrder();
        Order customOrder = mealOrder.scanInput(vegieAmount, glutenFreeAmount, nutFreeAmount, fishFreeAmount, totalMeal);
        mealOrder.placeOrder(customOrder, highRatingRestaurant, lowRatingRestaurant);

        //6. Print recommendation
        ArrayList <Restaurant> arrListRestaurant = new ArrayList<>();
        arrListRestaurant.add(highRatingRestaurant);
        arrListRestaurant.add(lowRatingRestaurant);
        mealOrder.printRecommendation(arrListRestaurant);
    }
}
