package com.example.reto3.Service;


import com.example.reto3.Model.Score;
import com.example.reto3.Repository.ScoreRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Crist
 */
@Service
public class ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> getAll() {
        return (List<Score>) scoreRepository.getAll();
    }

    public Optional<Score> getScore(int id) {
        return scoreRepository.getScore(id);
    }

    public Score save(Score score) {
        if (score.getIdScore()== null) {
            return scoreRepository.save(score);
        } else {
            Optional<Score> scoreEncontrado = scoreRepository.getScore(score.getIdScore());
            if (scoreEncontrado.isEmpty()) {
                return scoreRepository.save(score);
            } else {
                return score;
            }
        }
    }

    public Score update(Score score) {
        if (score.getIdScore()!= null) {
            Optional<Score> scoreEncontrado = scoreRepository.getScore(score.getIdScore());
            if (!scoreEncontrado.isEmpty()) {
                if (score.getMessageText()!= null) {
                    scoreEncontrado.get().setMessageText(score.getMessageText());
                }
                if (score.getStars()!= null) {
                    scoreEncontrado.get().setStars(score.getStars());
                }

                return scoreRepository.save(scoreEncontrado.get());
            }
        }
        return score;

    }

    public boolean deleteScore(int scoreId) {
        Boolean resultado = getScore(scoreId).map(scorePorEliminar -> {
            scoreRepository.delete(scorePorEliminar);
            return true;
        }).orElse(false);
        return resultado;
    }

}
