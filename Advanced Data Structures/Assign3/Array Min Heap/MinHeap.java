import BasicIO.*;

public class MinHeap
{
  private static ASCIIDataFile data; 
  public static int[] Heap;
  public static int size;
  private int maxsize;
  
  private static int FRONT = 1;
  
  public MinHeap(int maxsize)
  {
    this.maxsize = maxsize;
    this.size = 0;
    Heap = new int[this.maxsize + 1];
    Heap[0] = Integer.MIN_VALUE;
  }
  
  private int parent(int pos)
  {
    return pos / 2;
  }
  
  private int left(int pos)
  {
    return (2 * pos);
  }
  
  private int right(int pos)
  {
    return (2 * pos) + 1;
  }
  
  private boolean isLeaf(int pos)
  {
    if (pos >=  (size / 2)  &&  pos <= size)
    { 
      return true;
    }
    return false;
  }
  
  private void swap(int first, int second)
  {
    int tmp;
    tmp = Heap[first];
    Heap[first] = Heap[second];
    Heap[second] = tmp;
  }
  
  private void minHeapify(int pos)
  {
    if (!isLeaf(pos))
    { 
      if ( Heap[pos] > Heap[left(pos)]  || Heap[pos] > Heap[right(pos)])
      {
        if (Heap[left(pos)] < Heap[right(pos)])
        {
          swap(pos, left(pos));
          minHeapify(left(pos));
        }
        else
        {
          swap(pos, right(pos));
          minHeapify(right(pos));
        }
      }
    }
  }
  
  public void insert(int val)
  {
    Heap[++size] = val;
    int current = size;
    
    while (Heap[current] < Heap[parent(current)])
    {
      swap(current,parent(current));
      current = parent(current);
    } 
  }
  
  public void minHeap()
  {
    for (int pos = (size / 2); pos >= 1 ; pos--)
    {
      minHeapify(pos);
    }
  }
  
  public int pop()
  {
    int popped = Heap[FRONT];
    Heap[FRONT] = Heap[size--]; 
    minHeapify(FRONT);
    return popped;
  }
  
  public static void traverse(MinHeap minHeap, int n)
  {
    for(int i = 0; i < n;i++)
    {
      System.out.print(minHeap.pop() + " ");
    }
    System.out.println();
  }
  
  public static int deleteMin(int _size)
  {
    _size = _size -1;
    //sets the current array value to equal everything to the right of it
    for(int i = 0; i < _size; i++)
    {
      Heap[FRONT+i] = Heap[FRONT+i+1];
    }
    return _size;
  }
  
  public static void main(String...arg)
  {
    long start = System.currentTimeMillis();
    data = new ASCIIDataFile();
    int n = data.readInt();
    //fixes a glitch by having the minHeap always being 1 greater than the max starting array size
    MinHeap minHeap = new MinHeap(n+1);
    MinHeap test = minHeap;
    for(int i = 0; i < n; i++)
    {
      int val = data.readInt();
      minHeap.insert(val);
    }
    //deletes the minimum value and changes the new size to fit it

    int size = n;
    
    traverse(minHeap, n);
    minHeap = test;
        
    //each decreasing its size and deletes the minimum value and traverses it
    for(int i = 0; i < size; i++)
    {
      minHeap = test;
      n = deleteMin(n);
      traverse(minHeap, n);
    }
    System.out.println( System.currentTimeMillis() - start);
  }
}
