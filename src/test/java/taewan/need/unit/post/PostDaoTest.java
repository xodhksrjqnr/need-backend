package taewan.need.unit.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.jdbc.Sql;
import taewan.need.domain.post.entity.Post;
import taewan.need.domain.post.repository.PostDao;
import taewan.need.domain.post.repository.PostDaoImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(PostDaoImpl.class)
@DataJpaTest
@EnableJpaRepositories(basePackages = "taewan.need.domain.post.repository")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"/test-condition-data.sql", "/test-post-data.sql"})
public class PostDaoTest {

    @Autowired private PostDao postDao;

    @Test
    @DisplayName("job에 따른 필터 조회 테스트")
    void findAllWithFilter_with_job() {
        //given
        String job1 = "학생";
        String job2 = "취준생";
        String job3 = "invalid";

        //when
        List<Post> posts1 = postDao.findAllWithFilter(
                Sort.by("postId"), job1, "", "", "", "", 0L);
        List<Post> posts2 = postDao.findAllWithFilter(
                Sort.by("postId"), job2, "", "", "", "", 0L);
        List<Post> posts3 = postDao.findAllWithFilter(
                Sort.by("postId"), job3, "", "", "", "", 0L);

        //then
        assertEquals(posts1.size(), 10);
        assertEquals(posts2.size(), 0);
        assertEquals(posts3.size(), 0);
    }

    @Test
    @DisplayName("benefit에 따른 필터 조회 테스트")
    void findAllWithFilter_with_benefit() {
        //given
        String benefit1 = "취업";
        String benefit2 = "생활";
        String benefit3 = "invalid";

        //when
        List<Post> posts1 = postDao.findAllWithFilter(
                Sort.by("postId"), "", benefit1, "", "", "", 0L);
        List<Post> posts2 = postDao.findAllWithFilter(
                Sort.by("postId"), "", benefit2, "", "", "", 0L);
        List<Post> posts3 = postDao.findAllWithFilter(
                Sort.by("postId"), "", benefit3, "", "", "", 0L);

        //then
        assertEquals(posts1.size(), 10);
        assertEquals(posts2.size(), 0);
        assertEquals(posts3.size(), 0);
    }

    @Test
    @DisplayName("sex에 따른 필터 조회 테스트")
    void findAllWithFilter_with_sex() {
        //given
        String sex1 = "남성";
        String sex2 = "여성";
        String sex3 = "invalid";

        //when
        List<Post> posts1 = postDao.findAllWithFilter(
                Sort.by("postId"), "", "", sex1, "", "", 0L);
        List<Post> posts2 = postDao.findAllWithFilter(
                Sort.by("postId"), "", "", sex2, "", "", 0L);
        List<Post> posts3 = postDao.findAllWithFilter(
                Sort.by("postId"), "", "", sex3, "", "", 0L);

        //then
        assertEquals(posts1.size(), 10);
        assertEquals(posts2.size(), 1);
        assertEquals(posts3.size(), 1);
    }

    @Test
    @DisplayName("administrativeDistrict에 따른 필터 조회 테스트")
    void findAllWithFilter_with_administrativeDistrict() {
        //given
        String administrativeDistrict1 = "서울시";
        String administrativeDistrict2 = "종로구";
        String administrativeDistrict3 = "서울시 종로구";
        String administrativeDistrict4 = "invalid";

        //when
        List<Post> posts1 = postDao.findAllWithFilter(
                Sort.by("postId"), "", "", "", administrativeDistrict1, "", 0L);
        List<Post> posts2 = postDao.findAllWithFilter(
                Sort.by("postId"), "", "", "", administrativeDistrict2, "", 0L);
        List<Post> posts3 = postDao.findAllWithFilter(
                Sort.by("postId"), "", "", "", administrativeDistrict3, "", 0L);
        List<Post> posts4 = postDao.findAllWithFilter(
                Sort.by("postId"), "", "", "", administrativeDistrict4, "", 0L);

        //then
        assertEquals(posts1.size(), 10);
        assertEquals(posts2.size(), 0);
        assertEquals(posts3.size(), 10);
        assertEquals(posts4.size(), 0);
    }

    @Test
    @DisplayName("search에 따른 필터 조회 테스트")
    void findAllWithFilter_with_search() {
        //given
        String search1 = "test10";
        String search2 = "test";
        String search3 = "invalid";

        //when
        List<Post> posts1 = postDao.findAllWithFilter(
                Sort.by("postId"), "", "", "", "", search1, 0L);
        List<Post> posts2 = postDao.findAllWithFilter(
                Sort.by("postId"), "", "", "", "", search2, 0L);
        List<Post> posts3 = postDao.findAllWithFilter(
                Sort.by("postId"), "", "", "", "", search3, 0L);

        //then
        assertEquals(posts1.size(), 1);
        assertEquals(posts2.size(), 10);
        assertEquals(posts3.size(), 0);
    }

    @Test
    @DisplayName("필터 조건별 조합 조회 테스트")
    void findAllWithFilter() {
        /**
         * job      benefit    sex      administrativeDistrict    search
         * valid    valid      valid    valid                     valid
         * invalid  valid      valid    valid                     valid
         * valid    invalid    valid    valid                     valid
         * valid    valid      invalid  valid                     valid
         * valid    valid      valid    invalid                   valid
         * valid    valid      valid    valid                     invalid
         * invalid  invalid    valid    valid                     valid
         * invalid  valid      invalid  valid                     valid
         * invalid  valid      valid    invalid                   valid
         * invalid  valid      valid    valid                     invalid
         * valid    invalid    invalid  valid                     valid
         * valid    invalid    valid    invalid                   valid
         * valid    invalid    valid    valid                     invalid
         * valid    valid      invalid  invalid                   valid
         * valid    valid      invalid  valid                     invalid
         * valid    valid      valid    invalid                   invalid
         * invalid  invalid    invalid  valid                     valid
         * invalid  invalid    valid    invalid                   valid
         * invalid  invalid    valid    valid                     invalid
         * invalid  valid      invalid  invalid                   valid
         * invalid  valid      invalid  valid                     invalid
         * invalid  valid      valid    invalid                   invalid
         * valid    invalid    invalid  invalid                   valid
         * valid    invalid    invalid  valid                     invalid
         * valid    invalid    valid    invalid                   invalid
         * valid    valid      invalid  invalid                   invalid
         * invalid  invalid    invalid  invalid                   valid
         * invalid  invalid    invalid  valid                     invalid
         * invalid  invalid    valid    invalid                   invalid
         * invalid  valid      invalid  invalid                   invalid
         * valid    invalid    invalid  invalid                   invalid
         * invalid  invalid    invalid  invalid                   invalid
         */
        //when
        List<Post> posts1 = postDao.findAllWithFilter(
                Sort.by("postId"), "학생", "취업", "남성", "서울시 종로구", "test", 0L);
        List<Post> posts2 = postDao.findAllWithFilter(
                Sort.by("postId"), "invalid", "취업", "남성", "서울시 종로구", "test", 0L);
        List<Post> posts3 = postDao.findAllWithFilter(
                Sort.by("postId"), "학생", "invalid", "남성", "서울시 종로구", "test", 0L);
        List<Post> posts4 = postDao.findAllWithFilter(
                Sort.by("postId"), "학생", "취업", "invalid", "서울시 종로구", "test", 0L);
        List<Post> posts5 = postDao.findAllWithFilter(
                Sort.by("postId"), "학생", "취업", "남성", "invalid", "test", 0L);
        List<Post> posts6 = postDao.findAllWithFilter(
                Sort.by("postId"), "학생", "취업", "남성", "서울시 종로구", "invalid", 0L);
        List<Post> posts7 = postDao.findAllWithFilter(
                Sort.by("postId"), "invalid", "invalid", "남성", "서울시 종로구", "test", 0L);
        List<Post> posts8 = postDao.findAllWithFilter(
                Sort.by("postId"), "invalid", "취업", "invalid", "서울시 종로구", "test", 0L);
        List<Post> posts9 = postDao.findAllWithFilter(
                Sort.by("postId"), "invalid", "취업", "남성", "invalid", "test", 0L);
        List<Post> posts10 = postDao.findAllWithFilter(
                Sort.by("postId"), "invalid", "취업", "남성", "서울시 종로구", "invalid", 0L);
        List<Post> posts11 = postDao.findAllWithFilter(
                Sort.by("postId"), "학생", "invalid", "남성", "서울시 종로구", "test", 0L);
        List<Post> posts12 = postDao.findAllWithFilter(
                Sort.by("postId"), "학생", "invalid", "invalid", "서울시 종로구", "test", 0L);
        List<Post> posts13 = postDao.findAllWithFilter(
                Sort.by("postId"), "학생", "invalid", "남성", "invalid", "test", 0L);
        List<Post> posts14 = postDao.findAllWithFilter(
                Sort.by("postId"), "학생", "invalid", "남성", "서울시 종로구", "invalid", 0L);
        List<Post> posts15 = postDao.findAllWithFilter(
                Sort.by("postId"), "학생", "취업", "invalid", "invalid", "test", 0L);
        List<Post> posts16 = postDao.findAllWithFilter(
                Sort.by("postId"), "학생", "취업", "invalid", "서울시 종로구", "invalid", 0L);
        List<Post> posts17 = postDao.findAllWithFilter(
                Sort.by("postId"), "학생", "취업", "남성", "invalid", "invalid", 0L);
        List<Post> posts18 = postDao.findAllWithFilter(
                Sort.by("postId"), "invalid", "invalid", "invalid", "서울시 종로구", "test", 0L);
        List<Post> posts19 = postDao.findAllWithFilter(
                Sort.by("postId"), "invalid", "invalid", "남성", "invalid", "test", 0L);
        List<Post> posts20 = postDao.findAllWithFilter(
                Sort.by("postId"), "invalid", "invalid", "남성", "서울시 종로구", "invalid", 0L);
        List<Post> posts21 = postDao.findAllWithFilter(
                Sort.by("postId"), "invalid", "취업", "invalid", "invalid", "test", 0L);
        List<Post> posts22 = postDao.findAllWithFilter(
                Sort.by("postId"), "invalid", "취업", "invalid", "서울시 종로구", "invalid", 0L);
        List<Post> posts23 = postDao.findAllWithFilter(
                Sort.by("postId"), "invalid", "취업", "남성", "invalid", "invalid", 0L);
        List<Post> posts24 = postDao.findAllWithFilter(
                Sort.by("postId"), "학생", "invalid", "invalid", "invalid", "test", 0L);
        List<Post> posts25 = postDao.findAllWithFilter(
                Sort.by("postId"), "학생", "invalid", "invalid", "서울시 종로구", "invalid", 0L);
        List<Post> posts26 = postDao.findAllWithFilter(
                Sort.by("postId"), "학생", "취업", "invalid", "invalid", "invalid", 0L);
        List<Post> posts27 = postDao.findAllWithFilter(
                Sort.by("postId"), "invalid", "invalid", "invalid", "invalid", "test", 0L);
        List<Post> posts28 = postDao.findAllWithFilter(
                Sort.by("postId"), "invalid", "invalid", "invalid", "서울시 종로구", "invalid", 0L);
        List<Post> posts29 = postDao.findAllWithFilter(
                Sort.by("postId"), "invalid", "invalid", "남성", "invalid", "invalid", 0L);
        List<Post> posts30 = postDao.findAllWithFilter(
                Sort.by("postId"), "invalid", "취업", "invalid", "invalid", "invalid", 0L);
        List<Post> posts31 = postDao.findAllWithFilter(
                Sort.by("postId"), "학생", "invalid", "invalid", "invalid", "invalid", 0L);
        List<Post> posts32 = postDao.findAllWithFilter(
                Sort.by("postId"), "invalid", "invalid", "invalid", "invalid", "invalid", 0L);

        //then
        assertEquals(posts1.size(), 10);
        assertEquals(posts2.size(), 0);
        assertEquals(posts3.size(), 0);
        assertEquals(posts4.size(), 1);
        assertEquals(posts5.size(), 0);
        assertEquals(posts6.size(), 0);
        assertEquals(posts7.size(), 0);
        assertEquals(posts8.size(), 0);
        assertEquals(posts9.size(), 0);
        assertEquals(posts10.size(), 0);
        assertEquals(posts11.size(), 0);
        assertEquals(posts12.size(), 0);
        assertEquals(posts13.size(), 0);
        assertEquals(posts14.size(), 0);
        assertEquals(posts15.size(), 0);
        assertEquals(posts16.size(), 0);
        assertEquals(posts17.size(), 0);
        assertEquals(posts18.size(), 0);
        assertEquals(posts19.size(), 0);
        assertEquals(posts20.size(), 0);
        assertEquals(posts21.size(), 0);
        assertEquals(posts22.size(), 0);
        assertEquals(posts23.size(), 0);
        assertEquals(posts24.size(), 0);
        assertEquals(posts25.size(), 0);
        assertEquals(posts26.size(), 0);
        assertEquals(posts27.size(), 0);
        assertEquals(posts28.size(), 0);
        assertEquals(posts29.size(), 0);
        assertEquals(posts30.size(), 0);
        assertEquals(posts31.size(), 0);
        assertEquals(posts32.size(), 0);
    }

    @Test
    @DisplayName("게시물 전체 개수 조회 테스트")
    void count() {
        //when
        Long count = postDao.count();

        //then
        assertEquals(count, 10);
    }
}
