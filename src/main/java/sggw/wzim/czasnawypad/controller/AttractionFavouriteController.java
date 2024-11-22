package sggw.wzim.czasnawypad.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import sggw.wzim.czasnawypad.db.dto.FavouriteAttractionDTO;
import sggw.wzim.czasnawypad.model.dto.CreateFavouriteAttractionDTO;
import sggw.wzim.czasnawypad.service.FavouriteAttractionService;

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
