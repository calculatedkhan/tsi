import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("How many walls need to be painted");
        int walls = reader.nextInt();
        double total_painted_area = 0;
        for (int i = 1; i <= walls; ++i) {
            System.out.println("what shape is the wall " + i + "?");
            String shape = reader.next();
            double painted_area = surfaceArea(shape, true, i);
            if (painted_area == 0) {
                return;
            }
            System.out.println("How many non-painted areas are there?");
            int non_paint = reader.nextInt();
            total_painted_area = total_painted_area + painted_area;
            for (int j = 1; j <= non_paint; ++j) {
                double non_painted_area = surfaceArea(shape, false, j);
                total_painted_area = total_painted_area - non_painted_area;
            }
        }
        double amount_paint_needed = (total_painted_area/10)*2;
        System.out.println("What colour paint do you want?");
        String colour = reader.next();
        System.out.println("What brand of paint do you want? (Duluxe, Crown or Leyland)");
        String brand = reader.next();
        paint_requirement(brand, amount_paint_needed);
        //System.out.println("The amount of paint required in litres for 2 coats is " + amount_paint_needed);
        //System.out.println("The average cost of paint is £ " + (amount_paint_needed*5));
    }
    public static void paint_requirement(String brand, double paint_required) {
        int one_litre = 0;
        int five_litre = 0;
        int ten_litre = 0;
        int counter = 0;
        switch (brand) {
            case ("duluxe"):
                if (paint_required <= 1) {
                    System.out.println("You need a 1 litre tin, costing £15");
                    one_litre = one_litre + 1;
                } else if (paint_required <= 5) {
                    System.out.println("You need a 5 litre tin, costing £25");
                    five_litre = five_litre + 1;
                } else if (paint_required <= 10) {
                    System.out.println("You need a 10 litre tin, costing £35");
                    ten_litre = ten_litre + 1;
                } else {
                    System.out.println("You need a 10 litre tin, costing £35");
                    ten_litre = ten_litre + 1;
                    paint_required = paint_required - 10;
                    paint_requirement(brand, paint_required);
                }


        }
    }
    public static double surfaceArea(String shape, boolean is_painted, int i) {
        Scanner reader = new Scanner(System.in);
        if (is_painted) {
            switch (shape) {
                case ("square"):
                case ("rectangle"):
                    System.out.println("Whats the length of wall " + i);
                    double length = reader.nextInt();
                    System.out.println("Whats the width of wall " + i);
                    double width = reader.nextInt();
                    double total = length * width;
                    return total;
                case ("circle"):
                    System.out.println("Whats the radius of wall " + i);
                    double radius = reader.nextInt();
                    double total1 = radius * radius * Math.PI;
                    return total1;
                default:
                    System.out.println("This shape is unavailable");
                    return (0);
            }
        }
        else {
            System.out.println("Whats the length of non-painted area " + i);
            double length1 = reader.nextDouble();
            System.out.println("Whats the width of non-painted area " + i);
            double width1 = reader.nextDouble();
            double total2 = length1 * width1;
            return total2;

        }

    }
}

