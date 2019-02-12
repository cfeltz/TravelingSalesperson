PROJECT TITLE: Traveling Salesperson Problem
PURPOSE OF PROJECT: Implement two greedy heuristics in the attempt to find
an optimal solution to the Traveling Salesperson Problem
DATE: 12/14/2017
AUTHOR: Taylor Plummer and Christopher Feltz
ALGORITHM DESCRIPTIONS: 
    insertNearest()
	In this method we had to go through the linked list at least once. Right off the bat we know that this must be at least O(N) complexity. We will have an integer that will be remembering the smallest distance. While going through it, we have to check the distance between the point and the point within each node. If one of these is smaller than smallestDist, smallestDist will become that new distance, and we will have a ListNode that points to the node with the smallestDist. This in total, has a complexity of O(N), simply because we go through the LL once with one while loop. 
	
 
    insertSmallest()
	In this method we add the first 3 points because it does not technically matter where they go. Then we loop through all the the points in the list and compare the distances. For each point we find the distance from the original two points to each other, then we find the distance from the original two points to the new point. Then we subtract the original distance from the new distance and that will give us the increase in distance from adding the point. We find the lowest one and we add the point in there. We need to make a separate case for the end of the list when we are on the rear value of the list, so we do that by using our front and rear values.
Because we used a linked list with front and rear values as instance variables we got our insertSmallest method down to O(N).
