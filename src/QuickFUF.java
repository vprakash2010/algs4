import java.util.Scanner;

class QuickFUF{
  private int[] id;

  public QuickFUF(int N){
    id = new int[N];

    for(int i=0;i<id.length;i++)
      id[i] = i;
  }

  public boolean find(int p,int q){
    if(id[p]==id[q]){
      System.out.println(p +" is connected to "+q); 
      return true;
    }
    
      System.out.println(p +" is not connected to "+q); 
    return false;
  }


  public void union(int p,int q){
    
    for(int i =0;i<id.length;i++)
      if(id[i]==id[p]) 
        id[i] = id[q];   
  }

  public static void main(String[] args){
    
    Scanner in = new Scanner(System.in);
    int N = in.nextInt();

    QuickFUF obj  = new QuickFUF(N);
   try{

    while(in.hasNextInt())
    {
      int p = in.nextInt();
      int q = in.nextInt();

      if(!obj.find(p,q)){
          obj.union(p,q);

          System.out.println(p+" is now connected to "+q);
      }
    
    }
  }
  catch(ArrayIndexOutOfBoundsException e)
    {
        System.out.println("invalid index please enter 0 to"+ (N-1) +"\n quiting");
    }

  }

}
