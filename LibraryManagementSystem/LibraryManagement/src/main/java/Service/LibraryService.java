package Service;

import Model.Book;
import Model.Library;
import Model.Role;
import Model.Users;

public class LibraryService {
        Library library;
        Users currentUser=null;

        public LibraryService(Library library){
            this.library=library;
        }
        public void login(String username,String password){
           for( Users user:library.getUsers().values()){
               if(user.getuName().equals(username)&&
                  user.getPassword().equals(password)){
                   currentUser=user;
                   System.out.println("login Successfull");
                   System.out.println("Welcome "+user.getuName());
                   return;
               }
           }
           System.out.println("Invalid Credentials");

        }
        public void logout(){
            if(currentUser==null){
                System.out.println("No User logged in");
                return;
            }
            System.out.println("Logged out Successfully!!!");
            currentUser=null;
        }
        public void addUser(int id,String name,String username,String password,Role role){
            if(currentUser==null||currentUser.getRole()!=Role.ADMIN){
                System.out.println("Admin can only add members");
                return;
            }
              Users user=new Users();
              user.setUserId(id);
              user.setUserName(name);
              user.setuName(username);
              user.setPassword(password);
              user.setRole(role);
              library.getUsers().put(id,user);
              System.out.println("User added successfully");
        }
        public void addBook(int id,String title,String author){
           // Users user=library.getUsers().get(userId);
            if(currentUser==null){
                System.out.println("Login First");
                return;
            }
            if(currentUser.getRole()!= Role.ADMIN){
                System.out.println("Access Denied Only Admin can ADD Book");
                return;
            }
                  Book book=new Book(id,title,author);

                  library.getBooks().put(id,book);
                  System.out.println("Book added");
        }
        public void borrowBook(int bookId){

           Book book=library.getBooks().get(bookId);
           if(currentUser==null){
               System.out.println("Login to Continue");
               return;
           }
           if(book==null){
               System.out.println(" Book not Found");
               return;
           }
           if(currentUser.getRole()!=Role.MEMBER){
               System.out.println("Members can only Borrow Book");
               return;
           }
           if(!book.isAvailable()){
               System.out.println("Book Not Available");
               return;
           }
           currentUser.getBorrowedBook().put(bookId,book);
           book.setAvailable(false);
           System.out.println("Borrow Successful");
        }
        public void returnBook(int bookId){

            Book book=library.getBooks().get(bookId);
            if(currentUser==null){
                System.out.println("Login First");
                return;
            }
            if(book==null){
                System.out.println("Book not Found");
                return;
            }
           Book removed= currentUser.getBorrowedBook().remove(bookId);
            if(removed==null){
                System.out.println("User not borrowed the Book");
                return;
            }
            book.setAvailable(true);
            System.out.println("Return successful");
        }
        public void viewAllBooks(){
          for(  Book book:library.getBooks().values()){
              System.out.println("BookId:" +book.getBookId()+
                      "\n Title:"+book.getTitle()+"\n Author:"+book.getAuthor()+"\nAvailability:"+book.isAvailable());
          }

        }


}
