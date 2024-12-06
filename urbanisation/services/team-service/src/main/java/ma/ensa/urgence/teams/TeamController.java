package ma.ensa.urgence.teams;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/team")
@RestController
public class TeamController {

    private  final TeamService teamService;


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
