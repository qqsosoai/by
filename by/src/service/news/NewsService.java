package service.news;

import java.util.List;

import bean.News;

public interface NewsService {
	List<News> findByLimit(Integer pageIndex,Integer pageSize);
}
