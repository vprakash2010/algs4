import java.util.Scanner;                                                                                                          

class WQuickUF{
  private int[] id;
  private int[] size;

  public WQuickUF(int N){
    id = new int[N];
    size = new int[N];
    for(int i=0;i<id.length;i++)
    {
      id[i] = i;
      size[i] =1;
    }
  }

  public boolean find(int p,int q){
    return (root(p)==root(q));
  }

	private int root(int i){
		while(i!=id[i])i=id[i];
		
		return i;
 }

  private void union(int p,int q)
	{
    int i = root(p);
		int j = root(q);
    if(i!=j && size[j]>size[i])
    {
      id[i] =j;
      size[j] +=size[i];
    }    
    else
    {
      id[j] =i;
      size[i] += size[j];
    }      
  }

  public static void main(String[] args){
    
    Scanner in = new Scanner(System.in);
    int N = in.nextInt();
		int c,p,q;
    WQuickUF obj  = new WQuickUF(N);
 try{
		while(true)
		{		
			System.out.println("Enter for Union :0,Find :1,Exit :2");
			c = in.nextInt();
			 p = in.nextInt();
       q = in.nextInt();


			switch(c)
			{
      	case 0:
      		if(!obj.find(p,q))
					{

          	obj.union(p,q);

          	System.out.println(p+" is now connected to "+q);
      		}
					break;
				case 1:
						if( obj.find(p,q))
							System.out.println("Connected\n");
						else
							System.out.println("Not Connected\n");
						break;
				case 2:
						System.exit(1); 
			}

  }
 }
  catch(ArrayIndexOutOfBoundsException e)
    {
        System.out.println("invalid index please enter 0 to"+ (N-1) +"\n quiting");
    }

  }

}
