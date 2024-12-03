package sggw.wzim.czasnawypad.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import sggw.wzim.czasnawypad.db.dto.CreateFavouriteAttractionDTO;
import sggw.wzim.czasnawypad.db.dto.FavouriteAttractionDTO;
import sggw.wzim.czasnawypad.db.entity.User;
import sggw.wzim.czasnawypad.service.FavouriteAttractionService;


import java.util.List;

@RestController
@RequestMapping("/favourites")
@RequiredArgsConstructor
public class AttractionFavouriteController {

    private final FavouriteAttractionService favouriteService;

    @GetMapping
    ResponseEntity<?> getFavouritesInfo(@RequestHeader User user) {
        List<FavouriteAttractionDTO> favouriteInfo = favouriteService.getFavouritesByUser(user);
        return ResponseEntity.ok(favouriteInfo);
    }

    @PostMapping
    ResponseEntity<?> addFavourite(@RequestBody @Valid CreateFavouriteAttractionDTO toAdd) {
        FavouriteAttractionDTO result = favouriteService.addFavourite(toAdd);
        return ResponseEntity.created(URI.create("/" + result)).body(result);
    }


}
