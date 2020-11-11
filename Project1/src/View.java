public class View {

    public void viewingTheMap(){
        String[][] locations = GameManager.getGameLocations();
        for(int i = 0; i < 10; i += 2 ){
            if(i == 8){
                System.out.print("       **              **              **              **              **              **              **              **              **              **              **              **              **       \n" +
                                 "   *        *      *        *      *        *      *        *      *        *      *        *      *        *      *        *      *        *      *        *      *        *      *        *      *        *    \n" +
                                 "*              **              **              **              **              **              **              **              **              **              **              **              **              *\n" +
                                 "*    "+locations[i][0]+"          **     "+locations[i][1]+"         **     "+locations[i][2]+"         **     "+locations[i][3]+"         **     "+locations[i][4]+"         **     "+locations[i][5]+"         **     "+locations[i][6]+"         **     "+locations[i][7]+"         **     "+locations[i][8]+"         **     "+locations[i][9]+"         **     "+locations[i][10]+"         **     "+locations[i][11]+"         **     "+locations[i][12]+"         *\n" +
                                 "*              **              **              **              **              **              **              **              **              **              **              **              **              *\n" +
                                 "   *        *      *        *      *        *      *        *      *        *      *        *      *        *      *        *      *        *      *        *      *        *      *        *      *        *   \n" +
                                 "       **              **              **              **              **              **              **              **              **              **              **              **              **        \n");
                break;
            }
            System.out.print("       **              **              **              **              **              **              **              **              **              **              **              **              **       \n" +
                             "   *        *      *        *      *        *      *        *      *        *      *        *      *        *      *        *      *        *      *        *      *        *      *        *      *        *    \n" +
                             "*              **              **              **              **              **              **              **              **              **              **              **              **              *\n" +
                             "*    "+locations[i][0]+"     **     "+locations[i][1]+"    **     "+locations[i][2]+"    **     "+locations[i][3]+"    **     "+locations[i][4]+"         **     "+locations[i][5]+"         **     "+locations[i][6]+"         **     "+locations[i][7]+"         **     "+locations[i][8]+"         **     "+locations[i][9]+"         **     "+locations[i][10]+"         **     "+locations[i][11]+"         **     "+locations[i][12]+"         *\n" +
                             "*              **              **              **              **              **              **              **              **              **              **              **              **              *\n" +
                             "   *        *      *        *      *        *      *        *      *        *      *        *      *        *      *        *      *        *      *        *      *        *      *        *      *        *   \n" +
                             "       **              **              **              **              **              **              **              **              **              **              **              **              **        \n" +
                             "       **     "+locations[i + 1][0]+"         **     "+locations[i + 1][1]+"         **     "+locations[i + 1][2]+"         **     "+locations[i + 1][3]+"         **     "+locations[i + 1][4]+"         **     "+locations[i + 1][5]+"         **     "+locations[i + 1][6]+"         **     "+locations[i + 1][7]+"         **     "+locations[i + 1][8]+"         **     "+locations[i + 1][9]+"         **     "+locations[i + 1][10]+"         **     "+locations[i + 1][11]+"         **         \n");
        }
    }
}
