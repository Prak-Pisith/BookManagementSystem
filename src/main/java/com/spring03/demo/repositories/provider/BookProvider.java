package com.spring03.demo.repositories.provider;

import com.spring03.demo.model.Book;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;

public class BookProvider {

    public String getAllProvider(){
        return new SQL(){{
            SELECT("*");
            FROM("tb_book b");
            INNER_JOIN("tb_category t ON b.cate_id = t.id");
            ORDER_BY("b.cate_id asc");
        }}.toString();
    }

    public String createProvider(Book book){
        return new SQL(){{
            INSERT_INTO("tb_book");
            VALUES("title", "#{title}");
            VALUES("author", "#{author}");
            VALUES("publisher", "#{publisher}");
            VALUES("thumbnail", "#{thumbnail}");
            VALUES("cate_id","#{category.id}");
        }}.toString();
    }

    public String deleteProvider(Integer id){
        return new SQL(){{
            DELETE_FROM("tb_book b");
            WHERE("id=#{id}");
        }}.toString();
    }

    public String updateProvider(Book book){
        return new SQL(){{
            UPDATE("tb_book b");
            SET("title= #{title}");
            SET("author = #{author}");
            SET("publisher = #{publisher}");
            SET("thumbnail = #{thumbnail}");
            SET("cate_id = #{category.id");
            WHERE("id=#{id}");
        }}.toString();
    }
}
