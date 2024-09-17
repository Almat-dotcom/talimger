package kz.talimger.dto.school;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SchoolDTO {
    private AddressDTO address;
    private String address_name;
    private List<AdmDivDTO> adm_div;
    private String building_name;
    private String city_alias;
    private List<ContactGroupDTO> contact_groups;
    private ContextDTO context;
    private DatesDTO dates;
    private List<ExternalContentDTO> external_content;
    private FlagsDTO flags;
    private FloorsDTO floors;
    private String full_name;
    private GeometryDTO geometry;
    private List<GroupDTO> group;
    private String id;
    private boolean is_promoted;
    private LinksDTO links;
    private String locale;
    private String name;
    private NameExDTO name_ex;
    private OrgDTO org;
    private String poi_category;
    private PointDTO point;
    private String purpose_code;
    private String purpose_name;
    private String region_id;
    private ReviewsDTO reviews;
    private List<RubricDTO> rubrics;
    private ScheduleDTO schedule;
    private String segment_id;
    private StatDTO stat;
    private int timezone_offset;
    private String type;

    @Getter
    @Setter
    public static class AddressDTO {
        private String building_id;
        private String building_name;
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
        private String city_alias;
        private FlagsDTO flags;
        // Новые поля для поддержания district, settlement, и district_area
        private String district_id;
        private String settlement_id;
        private String district_area_id;

        @Getter
        @Setter
        public static class FlagsDTO {
            private boolean is_default;
            private boolean is_region_center;
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
            private String print_text;
            private String type;
            private String value;
            private String url;
        }
    }

    @Getter
    @Setter
    public static class ContextDTO {
        private List<StopFactorDTO> stop_factors;

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
        private String updated_at;
    }

    @Getter
    @Setter
    public static class ExternalContentDTO {
        private int count;
        private String main_photo_url;
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
        private int ground_count;
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
        private List<DatabaseEntranceDTO> database_entrances;
        private List<EntranceDTO> entrances;

        @Getter
        @Setter
        public static class DatabaseEntranceDTO {
            private GeometryDTO geometry;
            private String id;
            private boolean is_primary;
            private boolean is_visible_on_map;
        }

        @Getter
        @Setter
        public static class EntranceDTO {
            private GeometryDTO geometry;
            private String id;
            private boolean is_primary;
            private boolean is_visible_on_map;
        }
    }

    @Getter
    @Setter
    public static class NameExDTO {
        private String extension;
        private String primary;
        private String short_name;
    }

    @Getter
    @Setter
    public static class OrgDTO {
        private int branch_count;
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
        private double general_rating;
        private int general_review_count;
        private int general_review_count_with_stars;
        private boolean is_reviewable;
        private List<ItemDTO> items;

        @Getter
        @Setter
        public static class ItemDTO {
            private boolean is_reviewable;
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
        private String parent_id;
        private int short_id;
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
            private List<WorkingHourDTO> working_hours;

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
        private boolean is_advertised;
        private String rubr;
        private int source_type;
    }
}