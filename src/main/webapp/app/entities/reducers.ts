import videoUser from 'app/entities/video-user/video-user.reducer';
import bankDetails from 'app/entities/bank-details/bank-details.reducer';
import contact from 'app/entities/contact/contact.reducer';
import videoPost from 'app/entities/video-post/video-post.reducer';
import videoTag from 'app/entities/video-tag/video-tag.reducer';
import sponsor from 'app/entities/sponsor/sponsor.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  videoUser,
  bankDetails,
  contact,
  videoPost,
  videoTag,
  sponsor,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
