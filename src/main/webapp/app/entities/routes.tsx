import React from 'react';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import VideoUser from './video-user';
import BankDetails from './bank-details';
import Contact from './contact';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="video-user/*" element={<VideoUser />} />
        <Route path="bank-details/*" element={<BankDetails />} />
        <Route path="contact/*" element={<Contact />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
