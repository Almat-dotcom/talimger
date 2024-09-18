package kz.talimger.dto.institution;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InstitutionDTO {
    private AddressDTO address;

    @JsonProperty("address_name")
    private String addressName;

    @JsonProperty("adm_div")
    private List<AdmDivDTO> admDiv;

    @JsonProperty("building_name")
    private String buildingName;

    @JsonProperty("city_alias")
    private String cityAlias;

    @JsonProperty("contact_groups")
    private List<ContactGroupDTO> contactGroups;

    private ContextDTO context;
    private DatesDTO dates;

    @JsonProperty("external_content")
    private List<ExternalContentDTO> externalContent;

    private FlagsDTO flags;
    private FloorsDTO floors;

    @JsonProperty("full_name")
    private String fullName;

    private GeometryDTO geometry;
    private List<GroupDTO> group;
    private String id;

    @JsonProperty("is_promoted")
    private boolean isPromoted;

    private LinksDTO links;
    private String locale;
    private String name;

    @JsonProperty("name_ex")
    private NameExDTO nameEx;

    private OrgDTO org;

    @JsonProperty("poi_category")
    private String poiCategory;

    private PointDTO point;

    @JsonProperty("purpose_code")
    private String purposeCode;

    @JsonProperty("purpose_name")
    private String purposeName;

    @JsonProperty("region_id")
    private String regionId;

    private ReviewsDTO reviews;
    private List<RubricDTO> rubrics;
    private ScheduleDTO schedule;

    @JsonProperty("segment_id")
    private String segmentId;

    private StatDTO stat;

    @JsonProperty("timezone_offset")
    private int timezoneOffset;

    private String type;

    @Getter
    @Setter
    public static class AddressDTO {
        @JsonProperty("building_id")
        private String buildingId;

        @JsonProperty("building_name")
        private String buildingName;

        private List<ComponentDTO> components;

        @Getter
        @Setter
        public static class ComponentDTO {
            private String number;
            private String street;
            private String type;
        }
    }

    @Getter
    @Setter
    public static class AdmDivDTO {
        private String id;
        private String name;
        private String type;

        @JsonProperty("city_alias")
        private String cityAlias;

        private FlagsDTO flags;

        @JsonProperty("district_id")
        private String districtId;

        @JsonProperty("settlement_id")
        private String settlementId;

        @JsonProperty("district_area_id")
        private String districtAreaId;

        @Getter
        @Setter
        public static class FlagsDTO {
            @JsonProperty("is_default")
            private boolean isDefault;

            @JsonProperty("is_region_center")
            private boolean isRegionCenter;
        }
    }

    @Getter
    @Setter
    public static class ContactGroupDTO {
        private List<ContactDTO> contacts;
        private ScheduleDTO schedule;

        @Getter
        @Setter
        public static class ContactDTO {
            private String text;
            private String code;

            @JsonProperty("print_text")
            private String printText;

            private String type;
            private String value;
            private String url;
        }
    }

    @Getter
    @Setter
    public static class ContextDTO {
        @JsonProperty("stop_factors")
        private List<StopFactorDTO> stopFactors;

        @Getter
        @Setter
        public static class StopFactorDTO {
            private String name;
            private String type;
        }
    }

    @Getter
    @Setter
    public static class DatesDTO {
        @JsonProperty("updated_at")
        private String updatedAt;
    }

    @Getter
    @Setter
    public static class ExternalContentDTO {
        private int count;

        @JsonProperty("main_photo_url")
        private String mainPhotoUrl;

        private String subtype;
        private String type;
    }

    @Getter
    @Setter
    public static class FlagsDTO {
        private boolean photos;
    }

    @Getter
    @Setter
    public static class FloorsDTO {
        @JsonProperty("ground_count")
        private int groundCount;
    }

    @Getter
    @Setter
    public static class GeometryDTO {
        private String centroid;
        private String selection;
    }

    @Getter
    @Setter
    public static class GroupDTO {
        private String id;
        private String type;
    }

    @Getter
    @Setter
    public static class LinksDTO {
        @JsonProperty("database_entrances")
        private List<DatabaseEntranceDTO> databaseEntrances;

        private List<EntranceDTO> entrances;

        @Getter
        @Setter
        public static class DatabaseEntranceDTO {
            private GeometryDTO geometry;
            private String id;

            @JsonProperty("is_primary")
            private boolean isPrimary;

            @JsonProperty("is_visible_on_map")
            private boolean isVisibleOnMap;
        }

        @Getter
        @Setter
        public static class EntranceDTO {
            private GeometryDTO geometry;
            private String id;

            @JsonProperty("is_primary")
            private boolean isPrimary;

            @JsonProperty("is_visible_on_map")
            private boolean isVisibleOnMap;
        }
    }

    @Getter
    @Setter
    public static class NameExDTO {
        private String extension;
        private String primary;

        @JsonProperty("short_name")
        private String shortName;
    }

    @Getter
    @Setter
    public static class OrgDTO {
        @JsonProperty("branch_count")
        private int branchCount;

        private String id;
        private String name;
    }

    @Getter
    @Setter
    public static class PointDTO {
        private double lat;
        private double lon;
    }

    @Getter
    @Setter
    public static class ReviewsDTO {
        @JsonProperty("general_rating")
        private double generalRating;

        @JsonProperty("general_review_count")
        private int generalReviewCount;

        @JsonProperty("general_review_count_with_stars")
        private int generalReviewCountWithStars;

        @JsonProperty("is_reviewable")
        private boolean isReviewable;

        private List<ItemDTO> items;

        @Getter
        @Setter
        public static class ItemDTO {
            @JsonProperty("is_reviewable")
            private boolean isReviewable;
            private String tag;
        }
    }

    @Getter
    @Setter
    public static class RubricDTO {
        private String alias;
        private String id;
        private String kind;
        private String name;

        @JsonProperty("parent_id")
        private String parentId;

        @JsonProperty("short_id")
        private int shortId;
    }

    @Getter
    @Setter
    public static class ScheduleDTO {
        private DayDTO Fri;
        private DayDTO Mon;
        private DayDTO Sat;
        private DayDTO Sun;
        private DayDTO Thu;
        private DayDTO Tue;
        private DayDTO Wed;

        @Getter
        @Setter
        public static class DayDTO {
            @JsonProperty("working_hours")
            private List<WorkingHourDTO> workingHours;

            @Getter
            @Setter
            public static class WorkingHourDTO {
                private String from;
                private String to;
            }
        }
    }

    @Getter
    @Setter
    public static class StatDTO {
        private long adst;

        @JsonProperty("is_advertised")
        private boolean isAdvertised;

        private String rubr;

        @JsonProperty("source_type")
        private int sourceType;
    }
}