package com.junebay.plancard.card.dto;

import com.junebay.plancard.image.vo.Image;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author : IAN
 * @date : 2025-03-25
 * @description : ResponseDTO 에 매핑되는 CardDTO
 */
@Getter
@Setter
public class CardDTO {

    private long cardId;
    private String title;
    private String description;
    private String country;
    private String city;
    private List<String> theme;
    private String category;
    private int rating;
    private boolean scrap;
    private String mapLink;
    private List<Image> imageList;

    /**
     *  "cardId": "1",
     *  "title": "Eiffel Tower",
     *  "description": "A wrought iron lattice tower on the Champ de Mars in Paris, France.",
     *  "country": "France",
     *  "city": "Paris",
     *  "theme": ["night","elastic"],
     *  "category": "attraction",
     *  "rating": 4,
        "scrap": true,
     *  "mapLink": "https://googlemap.com/france/paris/972234",
     *  "imageList": [
     *     {
     *        "imageId": 1,
     *        "imageUrl": "https://example.com/eiffel.jpg",
     *        "alt": "루브르 이미지",
     *        "isMain": false
     *     }
     *  ]
     */
}
