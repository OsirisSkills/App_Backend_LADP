package fr.jehann.app.tools;

import fr.jehann.app.dto.ProjectDTO;
import fr.jehann.app.entities.Project;
import org.modelmapper.ModelMapper;


public class DtoTools {
    private static ModelMapper myMapper = new ModelMapper(); // TODO ajout de config pour personnaliser le mapping dto<>entity
    public static <TSource, TDestination> TDestination convert(TSource obj, Class<TDestination> clazz) {

//       myMapper.typeMap(Project.class, ProjectDTO.class).addMappings(mapper -> {
//            mapper.map(src -> src.getId(), ProjectDTO::setId);
//            mapper.map(src -> src.getCategoryId(), ProjectDTO::setCategoryId);
//
//        });

//        myMapper.typeMap(JobOfferContentDto.class, JobOfferContent.class).addMappings(mapper -> {
//            mapper.map(src -> src.getId(), JobOfferContent::setId);
//            mapper.map(src -> src.getTitle(), JobOfferContent::setTitle);
//            mapper.map(src -> src.getOffer(), JobOfferContent::setOffer);
//            mapper.map(src -> src.getSectionNumber(), JobOfferContent::setSectionNumber);
//        });

        return myMapper.map(obj, clazz);
    }
}