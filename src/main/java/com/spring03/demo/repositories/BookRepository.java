package com.spring03.demo.repositories;

import com.github.javafaker.Faker;
import com.spring03.demo.model.Book;
import com.spring03.demo.repositories.provider.BookProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface BookRepository {

    //Using Mybatis
//    @Select("Select * From tb_Book")
    @SelectProvider(type = BookProvider.class, method = "getAllProvider")
    @Results({
            @Result(column = "cate_id", property = "category.id"),
            @Result(column = "name", property = "category.name")
    })
    List<Book> getAll();

    @Select("Select * From tb_Book b inner join tb_category c ON b.cate_id = c.id Where b.id=#{id}")
    @Results({
            @Result(column = "cate_id", property = "category.id"),
            @Result(column = "name", property = "category.name")
    })
    Book findOneBook(@Param("id") Integer id);

/*    @UpdateProvider(type = BookProvider.class, method = "updateProvider")
    @Results({
            @Result(column = "cate_id", property = "category.id"),
            @Result(column = "name", property = "category.name")
    })*/
    @Update("Update tb_Book Set title=#{title}, author=#{author}, publisher=#{publisher}, thumbnail=#{thumbnail}, cate_id = #{category.id} Where id=#{id}")
    Boolean update(Book book);

//    @Delete("Delete From tb_Book Where id=#{id}")
    @DeleteProvider(type = BookProvider.class, method = "deleteProvider")
    @Results({
            @Result(column = "cate_id", property = "category.id"),
            @Result(column = "name", property = "category.name")
    })
    boolean delete(Integer id);

//    @Insert("Insert Into tb_Book (title,author,publisher,thumbnail) Values (#{title}, #{author}, #{publisher}, #{thumbnail})")
    @InsertProvider(type = BookProvider.class, method = "createProvider")
    @Results({
            @Result(column = "cate_id", property = "category.id"),
            @Result(column = "name", property = "category.name")
    })
    boolean create(Book book);


//    Faker faker = new Faker();
//    List<Book> bookList = new ArrayList<>();
//    {
//        for (int i=1;i<11;i++){
//            Book book = new Book();
//            book.setId(i);
//            book.setTitle(faker.book().title());
//            book.setAuthor(faker.book().author());
//            book.setPublisher(faker.book().publisher());
//
//            bookList.add(book);
//        }
//    }
//    public List<Book> getAll(){
//        return this.bookList;
//    }
//
//    public Book findOneBook(Integer id){
//        for(int i=0;i<bookList.size();i++){
//            if(bookList.get(i).getId()==id){
//                return bookList.get(i);
//            }
//        }
//        return null;
//    }
//
//    public Boolean update(Book book){
//        for (int i=0;i<bookList.size();i++){
//            if(bookList.get(i).getId()==book.getId()){
//                bookList.set(i,book);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public boolean delete(Integer id){
//        for(int i=0;i<bookList.size();i++){
//            if(bookList.get(i).getId()==id){
//                bookList.remove(i);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public boolean create(Book book){
//        return bookList.add(book);
//    }
}
