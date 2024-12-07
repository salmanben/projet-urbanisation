package ma.ensa.urgence.standards;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/team")
@RestController
public class TeamController {

    private  final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }


    @GetMapping("")
    public List<Team> getAll() {
        return teamService.getTeams();
    }

    @GetMapping("/{id}")
    public Team get(int id) {
        return teamService.getTeamById(id);
    }

    @GetMapping("/me")
    public Team get() {
        int id = 1;
        return teamService.getTeamById(id);
    }

}
