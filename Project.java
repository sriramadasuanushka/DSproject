package MyPackage;

import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.io.*;

// ---------------- FLIGHT CLASS ----------------
class Flight implements Comparable<Flight>{
    String number, from, to, depTime, date, airline;
    double cost;
    int seats;

    Flight(String n,String f,String t,String dt,String ar,String dat,double co,int s){
        number=n;
        from=f;
        to=t;
        depTime=dt;
        airline=ar;
        date=dat;
        cost=co;
        seats=s;
    }

    public int compareTo(Flight obj){
        int fromCompare = this.from.compareToIgnoreCase(obj.from);
        if(fromCompare == 0){
            return this.to.compareToIgnoreCase(obj.to);
        }
        return fromCompare;
    }

    public String toString(){
        return number+" ("+from+"-"+to+") "+depTime+" Cost:"+cost+" Seats:"+seats;
    }
}

// ---------------- SINGLY LINKED LIST FOR FLIGHTS ----------------
class FlightNode{
    Flight data;
    FlightNode next;
    FlightNode(Flight f){
        data=f;
        next=null;
    }
}

class FlightLinkedList{
    FlightNode head;

    void addFlight(Flight f){
        FlightNode newNode=new FlightNode(f);
        if(head==null){
            head=newNode;
            return;
        }
        FlightNode temp=head;
        while(temp.next!=null)
            temp=temp.next;
        temp.next=newNode;
    }

    void displayTable(){
        if(head==null){
            System.out.println("No Flights");
            return;
        }

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.printf("%-8s %-10s %-10s %-10s %-12s %-12s %-8s %-6s\n",
                "Flight","From","To","Time","Airline","Date","Cost","Seats");
        System.out.println("-------------------------------------------------------------------------------------");

        FlightNode temp=head;
        while(temp!=null){
            Flight f=temp.data;
            System.out.printf("%-8s %-10s %-10s %-10s %-12s %-12s %-8.0f %-6d\n",
                    f.number,f.from,f.to,f.depTime,f.airline,f.date,f.cost,f.seats);
            temp=temp.next;
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }

    Flight[] toArray(){
        int count=0;
        FlightNode temp=head;
        while(temp!=null){
            count++;
            temp=temp.next;
        }
        Flight[] arr=new Flight[count];
        temp=head;
        int i=0;
        while(temp!=null){
            arr[i++]=temp.data;
            temp=temp.next;
        }
        return arr;
    }
}

// ---------------- BOOKING CLASS ----------------
class Booking{
    String passengerName;
    String flightNo;
    Booking(String p,String f){
        passengerName=p;
        flightNo=f;
    }
    public String toString(){
        return passengerName+" booked "+flightNo;
    }
}

// ---------------- DOUBLY LINKED LIST FOR BOOKINGS ----------------
class BookingNode{
    Booking data;
    BookingNode prev;
    BookingNode next;
    BookingNode(Booking b){
        data=b;
        prev=null;
        next=null;
    }
}

class BookingDLL{
    BookingNode head;

    void addBooking(Booking b){
        BookingNode newNode=new BookingNode(b);
        if(head==null){
            head=newNode;
            return;
        }
        BookingNode temp=head;
        while(temp.next!=null)
            temp=temp.next;
        temp.next=newNode;
        newNode.prev=temp;
    }

    void displayTable(){
        if(head==null){
            System.out.println("No Bookings");
            return;
        }

        System.out.println("-----------------------------------------");
        System.out.printf("%-20s %-12s\n", "Passenger Name", "Flight No");
        System.out.println("-----------------------------------------");

        BookingNode temp=head;
        while(temp!=null){
            Booking b=temp.data;
            System.out.printf("%-20s %-12s\n", b.passengerName, b.flightNo);
            temp=temp.next;
        }
        System.out.println("-----------------------------------------");
    }
}

// ---------------- CHECK-IN QUEUE ----------------
class CheckInQueue{
    Queue<String> queue=new LinkedList<>();

    void joinQueue(String name){
        queue.add(name);
        System.out.println(name+" joined check-in queue.");
    }

    void processCheckIn(){
        if(queue.isEmpty()){
            System.out.println("No passengers in queue.");
            return;
        }
        String p=queue.remove();
        System.out.println(p+" check-in completed.");
    }

    void displayTable(){
        if(queue.isEmpty()){
            System.out.println("Queue is empty.");
            return;
        }

        System.out.println("-----------------------------");
        System.out.printf("%-20s\n","Passenger Name");
        System.out.println("-----------------------------");
        for(String p:queue)
            System.out.printf("%-20s\n",p);
        System.out.println("-----------------------------");
    }
}

// ---------------- GENERIC BINARY SEARCH ----------------
class BinarySearchGenerics<T extends Comparable<T>>{
    public int search(T[] array,T key,int n){
        int si=0;
        int ei=n-1;
        while(si<=ei){
            int mid=(si+ei)/2;
            if(key.compareTo(array[mid])==0)
                return mid;
            else if(key.compareTo(array[mid])<0)
                ei=mid-1;
            else
                si=si+1;
        }
        return -1;
    }
}

//-----------------------quick sort------------------------------
class QuickSort<T extends Comparable<T>> {
    void quickSort(T[] a, int l, int r) {
        if (l < r) {
            int p = partition(a, l, r);
            quickSort(a, l, p - 1);
            quickSort(a, p + 1, r);
        }
    }
    int partition(T[] a, int l, int r) {
        T p = a[l]; int i = l, j = r + 1;
        do {
            do { i++; } while (i <= r && a[i].compareTo(p) < 0);
            do { j--; } while (a[j].compareTo(p) > 0);
            if (i < j) { T t = a[i]; a[i] = a[j]; a[j] = t; }
        } while (i < j);
        { T t = a[l]; a[l] = a[j]; a[j] = t; } 
        return j;
    }
}

// ---------------- MERGE SORT (SORT BY TIME) ----------------
class MergeSort{
    void mergeSort(Flight[] a,int l,int r){
        if(l<r){
            int m=(l+r)/2;
            mergeSort(a,l,m);
            mergeSort(a,m+1,r);
            merge(a,l,m,r);
        }
    }

    void merge(Flight[] a,int l,int m,int r){
        Flight[] temp=Arrays.copyOfRange(a,l,r+1);
        int i=l;
        int j=m+1;
        int k=0;
        while(i<=m && j<=r){
            if(a[i].depTime.compareTo(a[j].depTime)<=0)
                temp[k++]=a[i++];
            else
                temp[k++]=a[j++];
        }
        while(i<=m)
            temp[k++]=a[i++];
        while(j<=r)
            temp[k++]=a[j++];
        for(i=l,k=0;i<=r;i++,k++)
            a[i]=temp[k];
    }
}

// ---------------- MAIN CLASS ----------------
public class DSproject{
    static FlightLinkedList flightList=new FlightLinkedList();
    static BookingDLL bookingList=new BookingDLL();
    static CheckInQueue checkQueue=new CheckInQueue();

    // FILE OPERATIONS (Flights, Bookings, Queue)
    static void loadFlightsFromFile(){
        try{
            BufferedReader br=new BufferedReader(new FileReader("src/project/flights.txt"));
            String line;
            while((line=br.readLine())!=null){
                String[] d=line.split(",");
                flightList.addFlight(new Flight(d[0],d[1],d[2],d[3],d[4],d[5],
                        Double.parseDouble(d[6]), Integer.parseInt(d[7])));
            }
            br.close();
        } catch(Exception e){ System.out.println(e); }
    }

    static void saveFlightsToFile(){
        try{
            BufferedWriter bw=new BufferedWriter(new FileWriter("src/project/flights.txt"));
            FlightNode temp=flightList.head;
            while(temp!=null){
                Flight f=temp.data;
                bw.write(f.number+","+f.from+","+f.to+","+f.depTime+","+f.airline+","+f.date+","+f.cost+","+f.seats);
                bw.newLine();
                temp=temp.next;
            }
            bw.close();
        } catch(Exception e){ 
         System.out.println("Error saving flights."); 
         }
    }

    static void loadBookingsFromFile(){
        try{
            BufferedReader br=new BufferedReader(new FileReader("src/project/bookings.txt"));
            String line;
            while((line=br.readLine())!=null){
                String[] d=line.split(",");
                bookingList.addBooking(new Booking(d[0],d[1]));
            }
            br.close();
        } catch(Exception e){ 
         System.out.println("Bookings file not found.");
         }
    }

    static void saveBookingsToFile(){
        try{
            BufferedWriter bw=new BufferedWriter(new FileWriter("src/project/bookings.txt"));
            BookingNode temp=bookingList.head;
            while(temp!=null){
                Booking b=temp.data;
                bw.write(b.passengerName+","+b.flightNo);
                bw.newLine();
                temp=temp.next;
            }
            bw.close();
        } catch(Exception e){ 
         System.out.println("Error saving bookings.");
         }
    }

    static void loadQueueFromFile(){
        try{
            BufferedReader br=new BufferedReader(new FileReader("src/project/queue.txt"));
            String line;
            while((line=br.readLine())!=null){
                checkQueue.queue.add(line);
            }
            br.close();
        } catch(Exception e){ 
         System.out.println("Queue file not found."); 
         }
    }

    static void saveQueueToFile(){
        try{
            BufferedWriter bw=new BufferedWriter(new FileWriter("src/project/queue.txt"));
            for(String p:checkQueue.queue){
                bw.write(p);
                bw.newLine();
            }
            bw.close();
        } catch(Exception e){
         System.out.println("Error saving queue."); 
         }
    }

    

    public static void main(String[] args) throws Exception{
        loadQueueFromFile();
        loadFlightsFromFile();
        loadBookingsFromFile();

        Scanner sc=new Scanner(System.in);
        int choice;

        do{
         System.out.println("\n=================== AIRLINE BOOKING SYSTEM ==================="); 
         System.out.printf("%-6s %-50s\n", "S.No", "Description"); 
         System.out.println("----------------------------------------");
         System.out.printf("%-6d %-50s\n", 1, "Add Flight"); 
         System.out.printf("%-6d %-50s\n", 2, "Display Flights"); 
         System.out.printf("%-6d %-50s\n", 3, "Quick Sort Flights (By Flight Number)");
         System.out.printf("%-6d %-50s\n", 4, "Merge Sort Flights (By Time)");
         System.out.printf("%-6d %-50s\n", 5, "Add Booking"); 
         System.out.printf("%-6d %-50s\n", 6, "Display Bookings");
         System.out.printf("%-6d %-50s\n", 7, "Join Check-in Queue");
         System.out.printf("%-6d %-50s\n", 8, "Process Check-in");
         System.out.printf("%-6d %-50s\n", 9, "Display Check-in Queue");
         System.out.printf("%-6d %-50s\n", 10, "Search Flights by Route & Date Range"); 
         System.out.printf("%-6d %-50s\n", 11, "Exit");
         System.out.println("=============================================================="); 
         System.out.print("Enter choice: ");
            
            choice=sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    System.out.print("Flight No: ");
                    String no = sc.nextLine();
                    System.out.print("From: ");
                    String from = sc.nextLine();
                    System.out.print("To: ");
                    String to = sc.nextLine();
                    System.out.print("Departure Time: ");
                    String time = sc.nextLine();
                    System.out.print("Airline: ");
                    String airline = sc.nextLine();
                    System.out.print("Date (DD-MM-YYYY): ");
                    String date = sc.nextLine();
                    System.out.print("Cost: ");
                    double cost = sc.nextDouble();
                    System.out.print("Seats: ");
                    int seats = sc.nextInt();
                    sc.nextLine();

                    flightList.addFlight(new Flight(no, from, to, time, airline, date, cost, seats));
                    saveFlightsToFile();
                    System.out.println("Flight Added!");
                    break;

                case 2:
                    flightList.displayTable();
                    break;

                case 3:
                 Flight[] qarr = flightList.toArray();
                    new QuickSort<Flight>().quickSort(qarr,0,qarr.length-1);
                    System.out.println("Flights Sorted By Flight Number:");
                    System.out.println("-------------------------------------------------------------------------------------");
                    System.out.printf("%-8s %-10s %-10s %-10s %-12s %-12s %-8s %-6s\n",
                            "Flight","From","To","Time","Airline","Date","Cost","Seats");
                    System.out.println("-------------------------------------------------------------------------------------");
                    for(Flight f : qarr){
                        System.out.printf("%-8s %-10s %-10s %-10s %-12s %-12s %-8.0f %-6d\n",
                                f.number,f.from,f.to,f.depTime,f.airline,f.date,f.cost,f.seats);
                    }
                    System.out.println("-------------------------------------------------------------------------------------");
                    break;

                case 4:
                    Flight[] marr = flightList.toArray();
                    new MergeSort().mergeSort(marr,0,marr.length-1);
                    System.out.println("Flights Sorted By Time:");
                    System.out.println("-------------------------------------------------------------------------------------");
                    System.out.printf("%-8s %-10s %-10s %-10s %-12s %-12s %-8s %-6s\n",
                            "Flight","From","To","Time","Airline","Date","Cost","Seats");
                    System.out.println("-------------------------------------------------------------------------------------");
                    for(Flight f : marr){
                        System.out.printf("%-8s %-10s %-10s %-10s %-12s %-12s %-8.0f %-6d\n",
                                f.number,f.from,f.to,f.depTime,f.airline,f.date,f.cost,f.seats);
                    }
                    System.out.println("-------------------------------------------------------------------------------------");
                    break;

                case 5:
                    System.out.print("Passenger Name: ");
                    String name=sc.nextLine();
                    System.out.print("Flight No: ");
                    String fno=sc.nextLine();
                    bookingList.addBooking(new Booking(name,fno));
                    saveBookingsToFile();
                    System.out.println("Booking Added!");
                    break;

                case 6:
                    bookingList.displayTable();
                    break;

                case 7:
                    System.out.print("Passenger Name: ");
                    String pname=sc.nextLine();
                    checkQueue.joinQueue(pname);
                    saveQueueToFile();
                    break;

                case 8:
                    checkQueue.processCheckIn();
                    saveQueueToFile();
                    break;

                case 9:
                    checkQueue.displayTable();
                    break;

                case 10:
                 
                    // --- Search Flights by Route & Date Range ---
                    System.out.print("From: "); 
                    String fromPlace = sc.nextLine();
                    System.out.print("To: "); 
                    String toPlace = sc.nextLine();
                    System.out.print("Enter Date (DD-MM-YYYY): "); 
                    String startDateStr = sc.nextLine();
                    System.out.print("Enter number of days range (+/-): "); 
                    int dayRange = sc.nextInt();
                    sc.nextLine();

                    SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
                    Date startDate = sdf2.parse(startDateStr);

                    FlightNode tempNode = flightList.head;

                    System.out.println("\nFlights available from " + fromPlace + " to " + toPlace + " within " + dayRange + " days:\n");
                    System.out.println("-------------------------------------------------------------------------------------");
                    System.out.printf("%-8s %-10s %-10s %-10s %-12s %-12s %-8s %-6s\n",
                            "Flight","From","To","Time","Airline","Date","Cost","Seats");
                    System.out.println("-------------------------------------------------------------------------------------");

                    while(tempNode != null){
                        Flight f = tempNode.data;
                        Date flightDate = sdf2.parse(f.date);
                        long diff = Math.abs(flightDate.getTime() - startDate.getTime());
                        long diffDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

                        if(f.from.equalsIgnoreCase(fromPlace) && f.to.equalsIgnoreCase(toPlace) && diffDays <= dayRange){
                            System.out.printf("%-8s %-10s %-10s %-10s %-12s %-12s %-8.0f %-6d\n",
                                    f.number,f.from,f.to,f.depTime,f.airline,f.date,f.cost,f.seats);
                        }

                        tempNode = tempNode.next;
                    }
                    System.out.println("-------------------------------------------------------------------------------------");
                    break;
            }

        }while(choice!=11);

        sc.close();
    }
}
