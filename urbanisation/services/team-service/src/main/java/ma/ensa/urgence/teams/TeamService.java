package ma.ensa.urgence.teams;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TeamService {

    private  final TeamDao teamDao;

    public List<Team> getTeams() {
        return teamDao.findAll();
    }

    public Team getTeamById(int id) {
        return teamDao.findById(id).orElse(null);
    }
}
