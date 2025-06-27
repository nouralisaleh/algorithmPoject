public class ProductManager {

        public class Product {
            int ID;
            String name;
            float price;
            int quantity;
            Product left;
            Product right;

            public Product(int ID, String name, float price, int quantity) {
                this.ID = ID;
                this.name = name;
                this.price = price;
                this.quantity = quantity;
                left = null;
                right = null;
            }
        }

        Product product;
        static int containerCapacity;

        public void addAProduct(int ID,String name,float price, int quantity)
        {
            containerCapacity+=quantity;
            if(containerCapacity>1000 || price<0)
            {
                System.out.println("over full container or Inavilable price !!!!");
            }

            product=innerAddingProduct(product,ID,name,price,quantity);
        }
        private Product innerAddingProduct(Product p,int ID,String name,float price, int quantity)
        {

            if(p==null)
                return new Product(ID,name,price,quantity);
            else if(ID<p.ID)
                p.left=innerAddingProduct(p.left, ID, name, price,  quantity);
            else if(ID>p.ID)
                p.right=innerAddingProduct(p.right,ID,name,price,quantity);
            else
                System.out.println("you have this product");

            return p;
        }

        public void searchProduct(int id)
        {
            Product p=innerSearching(product,id);
            if(p!=null)
                System.out.println("Product founded.");
            else
                System.out.println("No such ID like "+id+" !!!!");
        }
        private Product innerSearching(Product p,int id)
        {
            if(p!=null)
            {
                if(p.ID==id) return p;
                else if(id<p.ID)
                    return  innerSearching(p.left,id);
                else
                    return  innerSearching(p.right,id);
            }
            return null;
        }
        public void UpdateDetails(int id,float newUpdate,char typeOfChange)
        {
            Product p=innerSearching(product,id);
            if(typeOfChange=='p'|| typeOfChange=='P')
                p.price=newUpdate;
            else if(typeOfChange=='q'||typeOfChange=='Q')
                p.quantity=(int)newUpdate;
            else
                System.out.println("No such operation !!!");

        }

        public void delateProduct(int id)
        {
            product=innerDelation(product,id);
        }
        private Product findTheNextID(Product p)
        {
            if(p.left!=null)
                return findTheNextID(p.left);
            return p;

        }
        private Product innerDelation(Product p,int id)
        {
            if(p==null)
                return null;
            if(id<p.ID)
                p.left=innerDelation(p.left,id);
            else if( id>p.ID)
                p.right=innerDelation(p.right,id);
            else
            {
                if(p.left==null)
                    return p.right;
                if (p.right==null)
                    return p.left;
                Product nextProduct=findTheNextID(p.right);
                p.ID=nextProduct.ID;
                p.price=nextProduct.price;
                p.name=nextProduct.name;
                p.quantity=nextProduct.quantity;

                p.right=innerDelation(p.right,p.ID);
            }
            return p ;

        }

        public void showProduct()
        {
            innerPrintProduct(product);
        }
        private void innerPrintProduct(Product p)
        {
            if(p==null) return ;
            else
            {
                innerPrintProduct(p.left);
                System.out.println(p.name+"  "+p.price+"  "+p.quantity+"  "+p.ID);
                innerPrintProduct(p.right);
            }
        }



        



    }




