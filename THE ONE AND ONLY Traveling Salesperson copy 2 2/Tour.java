import java.util.*;
import java.awt.Graphics;

/**
 * This class is a specialized Linked List of Points that represents a
 * Tour of locations attempting to solve the Traveling Salesperson Problem
 * 
 * Author: Christopher Feltz and 
 * 1.0
 * comments with two sets of "//" indicates comments that were psuedo code
 */

public class Tour implements TourInterface
{
    // instance variables
    private ListNode front; //keeps track of the front of the tour
    private ListNode rear; //keeps track of the bottom of the tour
    private int size; //the logical size of the tour
                      //this is where the gravy of our code comes from. Because we are keeping track of rear throughout the code we do not put while loops to get
                      //to the end. Thus reducing the big-O complexity, making it more efficient.
                     

    // constructor
    public Tour()
    {
        front = null; //sets the front to null
        rear = null; //sets the rear to null
        size = 0; //sets the new logical size to zero
    }

    //return the number of points (nodes) in the list   
    public int size()
    {
        return size; //returns the logical size
    }

    // append Point p to the end of the list
    public void add(Point p)
    {
        ListNode newNode = new ListNode(p, null, rear);// new node that is pointing to rear.
        if(size == 0)//checks if the logical size of the array is zero. that way to see if there is something in the linked list.
        {
            front = newNode; //if there is nothing in the linked list then it makes the front point to the object that we are adding.
        }
        else//otherwise
        {
            rear.next = newNode;//it sets whatever rear is pointing at to point to new node.
        }
        rear = newNode; // rear now points to the node we added.
        size++;//increase size appropriately.
    } 

    // print every node in the list 
    public void print()
    {
        ListNode frontCopy = front;//makes a duplicate of front.
        while(frontCopy != null) //while there is something in front of frontCopy... 
        { 
            System.out.println(frontCopy.data);//prints out the information that the current node has         
            frontCopy = frontCopy.next;//makes frontcopy increase it's position.
        }   
    }

    // draw the tour using the given graphics context
    public void draw(Graphics g)
    {
        ListNode frontCopy = front;//create a duplicate of front
        int xNext = 0; //the coordinates of the next point
        int yNext = 0; //the coordinates of the next point
        while(frontCopy != null)
        {
            int x = (int)frontCopy.data.getX(); //gets the x coordinate from frontCopy
            int y = (int)frontCopy.data.getY(); //gets the y coordinate from frontCopy
            g.fillOval(x - 2,y - 2,5,5); //draws the dot
            if(frontCopy.next != null) //while there is something in front of frontcopy keep...
            {
                xNext = (int)frontCopy.next.data.getX();//make this the x coordinate of the data in front of frontCopy
                yNext = (int)frontCopy.next.data.getY();//make this the y coordinate of the data in front of frontCopy
            }
            else//if there is not something in front of frontCopy
            {
                xNext = (int)front.data.getX();//make this the current x coordinate of the data 
                yNext = (int)front.data.getY();//make this the current y coordinate of the data
            }
            g.drawLine(x, y, xNext, yNext);//draw line from current point to next point
            frontCopy = frontCopy.next;//increase the position of frontCopy
        }
    }

    //calculate the distance of the Tour, but summing up the distance between adjacent points
    //NOTE p.distance(p2) gives the distance where p and p2 are of type Point
    public double distance()
    {
        ListNode frontCopy = front;//Instantiation of a duplicate of front.
        double distance = 0;//distance that we are going to return
        while(frontCopy != null) //while there is something in front of frontcopy
        {
            Point x = frontCopy.data; //set the x coordinate to the current node's data
            Point y; //just make a point variable called y

            if(frontCopy.next != null) //if there is something in front of frontcopy
            {
                y = frontCopy.next.data; //set y to the orignal front data.
                
            }
            else
            {
                y = front.data;//set y to the thing after the front copy.

            }
            double add = x.distance(y);//the amount of distance that needs to be added to the distance that we are returning.
            distance = distance + add;//increase the distance that we are returning.

            frontCopy = frontCopy.next;//go to the next node in the linked list
        } 
        return distance; //return the distance
    }

    // add Point p to the list according to the NearestNeighbor heuristic
    public void insertNearest(Point p)
    {   
        // // go through linked list
        // // check the distance between the given point 
        // // and the oints in the LL.
        // // then remember the node with the smalles distance.
        // // create new node, with point p as it's point.
        // // have that pointing to the node in front of the node with smallest distance.
        // // make smallest distance node point to new node.
        // // make new node point back to smallest distance node. 
        // // make node in front of new node point to new node.
        // // if size == 0 then make a new listnode with the point p, increase size, make the list node pointing to nothing.
        // // compare

        if(size == 0)//check to see if there is nothing in the list
        {
            ListNode newNode = new ListNode(p, null, null);//create a new node in our list with point p as it's data. it is pointing to nothing
            front = newNode; //make front point to this node, since it is the first thing in our tour
            rear = newNode; //make rear point to this node, since it is the last thing in our tour
            size++; //increase size accordingly
            return;//stop it from doing more stuff.
        }
        else if(size == 1) //if there is only one thing in our linked list
        {
            add(p); //just add that sucker as a new node bb. since it will the first point and this point will be the nearest point to eachother.
        }
        else //if there is more than one thing in the linked list.
        {
            ListNode frontCopy = front; //create a duplicate of front
            ListNode smallestNode = front; //create a node to keep track of our smallest distance between p and the point we are checking.
            double smallestDist = front.data.distance(p);//create a double to check the distance between the first node,
            //and the point we are given
            while(frontCopy != null) //while there is something in front of frontcopy.
            {

                if(smallestDist > frontCopy.data.distance(p))//check to see if the smallestDistance is bigger than the distance between p and 
                //the current listnode's point
                {
                    smallestDist = frontCopy.data.distance(p);//make smallestDist equal to the distance between p and the current listnode's point
                    smallestNode = frontCopy;//make this node be remembered.
                }
                
                
                frontCopy = frontCopy.next;//make frontcopy increase it's position in the list.
            }
            
            
            if(smallestNode.next != null)//if there is something in front of the node we are remembering.
            {
                ListNode newNode = new ListNode(p, smallestNode.next, smallestNode); //make a newNode pointing at what smallestNode is pointing at, and makes it
                                                                                     //point to smallest node.
                smallestNode.next = newNode;//makes the thing in front of smallest node point to the newNode
            }
            else//if there is nothign in front of what smallest node is pointing at
            {
                ListNode xNode = new ListNode(p,smallestNode.next, smallestNode);//make a newNode pointing at what smallestNode is pointing at, and makes it
                                                                                 //point to smallest node.
                smallestNode.next = xNode;//makes the thing in front of smallest nod epoint to the newNode.
                rear = xNode;//make rear point at the new node that is at the end of the list.
            }
            size++;//increase size accordingly
        }
        return;//stops it from doing more things. this is completely useless.
        // // if(size == 0)
        // // {
        // // ListNode newNode = new ListNode(p, null, null);
        // // front = newNode;
        // // rear = newNode;
        // // size++;
        // // }
        // // else
        // // {
        // // ListNode frontCopy = front;
        // // ListNode pointing = front;
        // // double plum = frontCopy.data.distance(p);
        // // while(frontCopy != null)
        // // {

        // // if(plum > frontCopy.next.data.distance(p))
        // // {
        // // plum = frontCopy.next.data.distance(p);
        // // pointing.next = frontCopy.next;
        // // }
        // // frontCopy = frontCopy.next;
        // // }
        // // ListNode xNode = new ListNode(p,pointing.next, pointing);
        // // pointing.next = xNode;
        // // xNode.next.next = xNode;
        // // size++;
        // // }
    }

    // add Point p to the list according to the InsertSmallest heuristic
    public void insertSmallest(Point p)
    { 
        // //go through the list.
        // //add all of the distances together
        ListNode frontCopy = front;//creates a duplicate of node.
        ListNode remember = rear;//creates a new node that we are using to remember where we want to put the point p.
        double smallestD = 2147483647;//this is the largest number that we can have. so it will change no matter what on the first time we compare it.
        ListNode newNode = new ListNode(p, null, null);//creates a new node with point p as it's information. it is not pointing to anything.
        if(size == 0)//if there is nothing in this linked list.
        {
            ListNode new1 = new ListNode(p, null, null);//create a new node that points to nothing
            front = new1;//make the front point to this new node since it is the first thing in the linked list.
            rear = new1; //make the rear point to this new node since it is the last thing in the linked list.
            size++;//increase the size accordingly.
        }
        else if(size == 1)//so if the size is equal to one, we know that we have to add the point, since there is only one other point in the list.
        {
            add(p);
        }
        // //everytime you get a new point check it to the previous point and the point in front of it.
        // // if the distance between p and A are smaller than the distance between A and B, then make A point forward to p and B point backward to p.
        // // if it is smaller then dont do anything. and keep checking.
        else//if the list has more than one node...
        {
            while(frontCopy.next != null)//while there is something in front of frontCopy...
            {
                double oldD = frontCopy.data.distance(frontCopy.next.data);//create a double that will keep track of the distance between the frontcopy and the 
                                                                           //point in front of frontcopy
                double newD = p.distance(frontCopy.data) + p.distance(frontCopy.next.data); //create a second double that will keep track of the distance between
                                                                                            //the point and the frontcopy PLUS the distance between the point and
                                                                                            //the point in front of frontcopy therefore this would be the new
                                                                                            //distance if the point was there.
                if(newD - oldD <= smallestD || smallestD < 0)//if the increase is smaller than the current smallest increase OR smallest D is less than zero
                                                             //smallestD should not be less than zero... sooooo.....
                {
                    smallestD = newD - oldD;//smallestD will keep track of the smallest distance that is added. this is actually the distance btwn the point
                                            //and the point in front of it.
                    remember = frontCopy;   //remember will now remember the node where the smallest distance increase would be.
                }
                frontCopy = frontCopy.next;
            }
            double oldD = frontCopy.data.distance(front.data);// this then sets oldD to the distance between frontcopy,which is now at the end
            double newD = p.distance(front.data) + frontCopy.data.distance(p); //this sets it between the distance frontCopy.data.distance(front.data)
            if(newD - oldD <= smallestD)//if it is the smallest increase
            {
                frontCopy.next = newNode;//put it on the end.
                newNode = rear; //makes the new node point to the end of the tour.
            }   // this if statement is setting the last point in the tour.
            else
            {
                newNode.next = remember.next; //makes the thing in front of newNode point back at newNode
                remember.next = newNode; // makes the thing behind remember point at newNode
            }

            size++;//increases size 
        }
    }
    // This is a private inner class, which is a separate class within a class.
    private class ListNode
    {
        private Point data;//the information that the ListNode has
        private ListNode next;//what the listnode is pointing at (in front)
        private ListNode before;//what the listnode is pointing at (behind)
        public ListNode(Point p, ListNode n, ListNode b)//constructor for a doubly linked list.
        {
            this.data = p;//sets the information 
            this.next = n;//sets what the listnode is pointing to (front)
            this.before = b;//sets what the listnode is point to (behind)
        }

        public ListNode(Point p)//a second constructor for a listnode that does not have anything to point at.
        {
            this(p, null, null);//makes a list node, with information, however it does not have anything that it is pointing at behind or front.
        }   
        
    }
}