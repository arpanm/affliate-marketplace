import React from 'react';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import VideoUser from './video-user';
import BankDetails from './bank-details';
import Contact from './contact';
import VideoPost from './video-post';
import VideoTag from './video-tag';
import Sponsor from './sponsor';
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
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
