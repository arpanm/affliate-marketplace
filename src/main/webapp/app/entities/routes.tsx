import React from 'react';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import VideoUser from './video-user';
import BankDetails from './bank-details';
import Contact from './contact';
import VideoPost from './video-post';
import VideoTag from './video-tag';
import Sponsor from './sponsor';
import Competition from './competition';
import Prize from './prize';
import CompetitionWinner from './competition-winner';
import CompetitionPaymentFromSponsor from './competition-payment-from-sponsor';
import CompetitionPaymentToWinner from './competition-payment-to-winner';
import Review from './review';
import Affinity from './affinity';
import VideoPostChangeHistory from './video-post-change-history';
import ReviewChangeHistory from './review-change-history';
import AiTool from './ai-tool';
import AiToolSession from './ai-tool-session';
import AiToolChat from './ai-tool-chat';
import AiToolPayment from './ai-tool-payment';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="video-user/*" element={<VideoUser />} />
        <Route path="bank-details/*" element={<BankDetails />} />
        <Route path="contact/*" element={<Contact />} />
        <Route path="video-post/*" element={<VideoPost />} />
        <Route path="video-tag/*" element={<VideoTag />} />
        <Route path="sponsor/*" element={<Sponsor />} />
        <Route path="competition/*" element={<Competition />} />
        <Route path="prize/*" element={<Prize />} />
        <Route path="competition-winner/*" element={<CompetitionWinner />} />
        <Route path="competition-payment-from-sponsor/*" element={<CompetitionPaymentFromSponsor />} />
        <Route path="competition-payment-to-winner/*" element={<CompetitionPaymentToWinner />} />
        <Route path="review/*" element={<Review />} />
        <Route path="affinity/*" element={<Affinity />} />
        <Route path="video-post-change-history/*" element={<VideoPostChangeHistory />} />
        <Route path="review-change-history/*" element={<ReviewChangeHistory />} />
        <Route path="ai-tool/*" element={<AiTool />} />
        <Route path="ai-tool-session/*" element={<AiToolSession />} />
        <Route path="ai-tool-chat/*" element={<AiToolChat />} />
        <Route path="ai-tool-payment/*" element={<AiToolPayment />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
