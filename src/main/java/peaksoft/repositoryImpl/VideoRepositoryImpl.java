package peaksoft.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Lesson;

import peaksoft.model.Video;
import peaksoft.repository.VideoRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class VideoRepositoryImpl implements VideoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Video> getAllVideo() {
        return entityManager.createQuery("select c from Video c ").getResultList();
    }

    @Override
    public void addVideo(Long id, Video video) {
        Lesson lesson = entityManager.find(Lesson.class, id);
        lesson.setVideo(video);
        video.setLesson(lesson);
        entityManager.merge(video);

    }

    @Override
    public Video getById(Long id) {
        return entityManager.find(Video.class, id);
    }

    @Override
    public List<Video> getVideoByLessonId(Long id) {
        return entityManager.createQuery
                ("select c from Video c where c.lesson.id=:id", Video.class).setParameter("id", id).getResultList();
    }

    @Override
    public void updateVideo(Long id,Video video) {
        Video video1= entityManager.find(Video.class,id);
        video1.setVideoName(video.getVideoName());
        video1.setId(video.getId());
        video1.setLink(video.getLink());
        entityManager.merge(video1);

    }

    @Override
    public void deleteVideo(Long id) {
        Video video = entityManager.find(Video.class,id);
        video.setLesson(null);
        entityManager.remove(video);

    }
}
