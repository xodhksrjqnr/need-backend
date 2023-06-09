package taewan.need.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taewan.need.domain.post.dto.PostInfoDto;
import taewan.need.domain.post.repository.PostDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

    private final PostDao postDao;


    @Override
    public List<PostInfoDto> findAllWithFilter(Sort sort, String job, String benefit, String sex, String administrativeDistrict,
                                               String search, Long offset) {
        return postDao.findAllWithFilter(sort, job, benefit, sex, administrativeDistrict, search, offset)
                .stream().map(p -> {
                    List<String> conditions = new ArrayList<>();

                    if (!p.getAdministrativeDistrict().equals("무관")) {
                        String[] district = p.getAdministrativeDistrict().split(" ");

                        for (String d : district) {
                            conditions.add(d);
                        }
                    }
                    if (!p.getJob().getName().equals("무관")) {
                        conditions.add(p.getJob().getName());
                    }
                    if (!p.getBenefit().getName().equals("무관")) {
                        conditions.add(p.getBenefit().getName());
                    }
                    if (!p.getSex().equals("무관")) {
                        conditions.add(p.getSex());
                    }

                    return p.toInfoDto(conditions);
                }).toList();
    }
}
