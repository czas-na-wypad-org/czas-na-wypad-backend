package sggw.wzim.czasnawypad.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sggw.wzim.czasnawypad.db.dto.CreateFavouriteAttractionDTO;
import sggw.wzim.czasnawypad.db.dto.FavouriteAttractionDTO;
import sggw.wzim.czasnawypad.db.entity.User;
import sggw.wzim.czasnawypad.service.FavouriteAttractionService;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class AttractionFavouriteController {

    private final FavouriteAttractionService favouriteService;
    
    @GetMapping
    public List<FavouriteAttractionDTO> getUserFavourites(@AuthenticationPrincipal User user) {
        return favouriteService.getFavouritesByUser(user);
    }

    @PostMapping
    public FavouriteAttractionDTO addFavourite(@AuthenticationPrincipal User user,
                                        @Valid @RequestBody CreateFavouriteAttractionDTO dto) {
        return favouriteService.addFavourite(user, dto);
    }

}
