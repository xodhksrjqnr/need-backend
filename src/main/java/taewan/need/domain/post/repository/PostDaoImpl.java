package taewan.need.domain.post.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import taewan.need.domain.post.entity.Post;

import java.util.List;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostDaoImpl implements PostDao {

    private final EntityManager em;

    @Override
    public List<Post> findAllWithFilter(Sort sort, String job, String benefit, String sex, String administrativeDistrict,
                                        String search, Long offset) {
        String query = "select p.post_id, p.title, p.thumbnail, p.url, j.job_id, j.name, b.benefit_id, b.name, " +
                "p.administrative_district, p.sex, p.status, p.closing_date, p.created_date, p.last_modified_date " +
                "from posts as p " +
                "join benefits as b on p.benefit_id=b.benefit_id " +
                "join jobs as j on p.job_id=j.job_id " +
                "where p.status=1 " +
                (job.equals("") ? "" : ("and j.name in ('" + job + "', '무관') ")) +
                (benefit.equals("") ? "" : ("and b.name in ('" + benefit + "', '무관') ")) +
                (sex.equals("") ? "" : ("and p.sex in ('" + sex + "', '무관') ")) +
                (administrativeDistrict.equals("") ? "" : ("and (p.administrative_district like '" + administrativeDistrict + "%' or p.administrative_district like '무관') ")) +
                (search.equals("") ? "" : ("and p.title like '%" + search + "%' ")) +
                createSort(sort) +
                " limit 20 offset " + offset;

        return em.createNativeQuery(query, Post.class)
                .getResultList();
    }

    @Override
    public long count() {
        return ((Number) em.createQuery("select count(p) from Post p")
                .getSingleResult())
                .longValue();
    }

    private String createSort(Sort sort) {
        StringBuilder sortSql = new StringBuilder();
        List<Sort.Order> orders = sort.get().toList();
        int size = orders.size();

        for (Sort.Order o : orders) {
            sortSql
                    .append(" ")
                    .append(toColumnName(o.getProperty()))
                    .append(" ")
                    .append(o.getDirection());
            if (size > 1) {
                sortSql.append(",");
                size--;
            }
        }
        if (sortSql.length() != 0) {
            sortSql.insert(0, " order by");
        }
        return sortSql.toString();
    }

    private String toColumnName(String target) {
        StringBuilder sb = new StringBuilder(target);
        int index = 0;

        while (index < sb.length()) {
            char c = sb.charAt(index);

            if ('A' <= c && c <= 'Z') {
                sb.setCharAt(index, (char)(c + 32));
                sb.insert(index, '_');
                index++;
            }
            index++;
        }
        return sb.toString();
    }
}
