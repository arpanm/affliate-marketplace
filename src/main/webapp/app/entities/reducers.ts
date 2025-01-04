import videoUser from 'app/entities/video-user/video-user.reducer';
import bankDetails from 'app/entities/bank-details/bank-details.reducer';
import contact from 'app/entities/contact/contact.reducer';
import videoPost from 'app/entities/video-post/video-post.reducer';
import videoTag from 'app/entities/video-tag/video-tag.reducer';
import sponsor from 'app/entities/sponsor/sponsor.reducer';
import competition from 'app/entities/competition/competition.reducer';
import prize from 'app/entities/prize/prize.reducer';
import competitionWinner from 'app/entities/competition-winner/competition-winner.reducer';
import competitionPaymentFromSponsor from 'app/entities/competition-payment-from-sponsor/competition-payment-from-sponsor.reducer';
import competitionPaymentToWinner from 'app/entities/competition-payment-to-winner/competition-payment-to-winner.reducer';
import review from 'app/entities/review/review.reducer';
import affinity from 'app/entities/affinity/affinity.reducer';
import videoPostChangeHistory from 'app/entities/video-post-change-history/video-post-change-history.reducer';
import reviewChangeHistory from 'app/entities/review-change-history/review-change-history.reducer';
import aiTool from 'app/entities/ai-tool/ai-tool.reducer';
import aiToolSession from 'app/entities/ai-tool-session/ai-tool-session.reducer';
import aiToolChat from 'app/entities/ai-tool-chat/ai-tool-chat.reducer';
import aiToolPayment from 'app/entities/ai-tool-payment/ai-tool-payment.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  videoUser,
  bankDetails,
  contact,
  videoPost,
  videoTag,
  sponsor,
  competition,
  prize,
  competitionWinner,
  competitionPaymentFromSponsor,
  competitionPaymentToWinner,
  review,
  affinity,
  videoPostChangeHistory,
  reviewChangeHistory,
  aiTool,
  aiToolSession,
  aiToolChat,
  aiToolPayment,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
