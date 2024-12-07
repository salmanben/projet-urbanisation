package ma.ensa.urgence.standards;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private  final TeamDao teamDao;

    public TeamService(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    public List<Team> getTeams() {
        return teamDao.findAll();
    }

    public Team getTeamById(int id) {
        return teamDao.findById(id).orElse(null);
    }
}
